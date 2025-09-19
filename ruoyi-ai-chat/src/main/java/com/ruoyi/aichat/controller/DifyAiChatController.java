package com.ruoyi.aichat.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.bizcase.service.IBizCasePromptService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.Base64;

import com.ruoyi.bizcase.domain.BizConsultationSeMessages;
import com.ruoyi.bizcase.service.IBizConsultationSeMessagesService;

@Api(tags = "Dify AI 对话接口")
@RestController
@RequestMapping("/dify")
public class DifyAiChatController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(DifyAiChatController.class);
    @Value("${dify.api.url}")
    private String difyApiUrl;


    // 豆包语音识别配置（若未配置 key 将返回 501）
    @Value("${doubao.api.url:https://ark.cn-beijing.volces.com/api/v3}")
    private String doubaoApiUrl;
    @Value("${doubao.api.key:}")
    private String doubaoApiKey;
    @Value("${doubao.api.model:speech-01}")
    private String doubaoApiModel;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IBizCasePromptService bizCasePromptService;

    // 新增：问诊消息保存服务
    @Autowired
    private IBizConsultationSeMessagesService bizConsultationSeMessagesService;


    /**
     * 场景：开始与病例的对话
     * Dify,对话，问诊记录，病例，步骤
     *
     * @param query
     * @param conversationId
     * @param user
     * @param caseId
     * @param stepId
     * @return
     */
    @ApiOperation(value = "AI对话接口", notes = "与 Dify AI 进行对话，返回 AI 回复内容；若携带 sessionId，将把问和答写入对话明细表")
    @PostMapping("/chat")
    public AjaxResult chat(@ApiParam(value = "用户输入问题", required = true) @RequestParam String query, @ApiParam(value = "Dify 会话ID，可选") @RequestParam(required = false) String conversationId, @ApiParam(value = "用户标识", required = true) @RequestParam String user, @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long caseId, @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long stepId, @ApiParam(value = "系统会话ID，可选", required = true) @RequestParam(value = "sessionId") Long sessionId) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(caseId, stepId);
        if (apiKey == null || apiKey.isEmpty()) {
            return AjaxResult.error(HttpStatus.NOT_FOUND.value(), "API key not found");
        }
        String url = difyApiUrl + "/chat-messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("inputs", new HashMap<>());
        body.put("query", query);
        body.put("response_mode", "blocking");
        body.put("conversation_id", conversationId == null ? "" : conversationId);
        body.put("user", user);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        stopWatch.stop();

        String aiAnswer = null;
        try {
            JSONObject root = JSON.parseObject(response.getBody(), JSONObject.class);
            if (root != null) {
                aiAnswer = root.getString("answer");
                if ((aiAnswer == null || aiAnswer.isEmpty()) && root.getJSONObject("data") != null) {
                    aiAnswer = root.getJSONObject("data").getString("answer");
                }
            }
        } catch (Exception e) {
            log.warn("解析AI回复失败: {}", e.getMessage());
            return AjaxResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "AI回复解析失败: " + e.getMessage());
        }

        // 将问（学生）与答（患者）写入对话明细表
        try {
            if (sessionId != null) {
                int nextOrder = getNextMessageOrder(sessionId);
                Date now = new Date();
                // 学生发问
                BizConsultationSeMessages userMsg = new BizConsultationSeMessages();
                userMsg.setSessionId(String.valueOf(sessionId));
                userMsg.setMessageOrder(String.valueOf(nextOrder));
                userMsg.setSenderType(2L); // 2: 学生
                userMsg.setMessageType("text");
                userMsg.setMessageContent(query);
                userMsg.setTimestamp(now);
                userMsg.setCreateBy(SecurityUtils.getUsername());
                userMsg.setCreateTime(now);
                bizConsultationSeMessagesService.insertBizConsultationSeMessages(userMsg);

                // 患者回答（AI）
                BizConsultationSeMessages aiMsg = new BizConsultationSeMessages();
                aiMsg.setSessionId(String.valueOf(sessionId));
                aiMsg.setMessageOrder(String.valueOf(nextOrder + 1));
                aiMsg.setSenderType(1L); // 1: 患者
                aiMsg.setMessageType("text");
                aiMsg.setMessageContent(aiAnswer != null ? aiAnswer : "");
                aiMsg.setTimestamp(new Date());
                aiMsg.setResponseTime(String.valueOf(stopWatch.getTotalTimeMillis())); // 秒
                aiMsg.setCreateBy("AI");
                aiMsg.setCreateTime(new Date());
                bizConsultationSeMessagesService.insertBizConsultationSeMessages(aiMsg);
            }
        } catch (Exception e) {
            log.warn("保存问答对话失败，sessionId={}, error= {}", sessionId, e.getMessage());
            return AjaxResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "问答保存失败: " + e.getMessage());
        }
        return new AjaxResult(response.getStatusCodeValue(), response.getBody());
    }

    /**
     * 场景：开始与病例的对话
     * Dify,case,step,session,sessionmessage
     */
    @ApiOperation(value = "获取 Dify 参数列表", notes = "根据 caseId 和 stepId 获取 Dify 参数；若携带 sessionId，将自动解析开场白并入库")
    @GetMapping("/parameters")
    public AjaxResult getParameters(@ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long case_id, @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long step_id, @ApiParam(value = "会话ID", required = true) @RequestParam(value = "sessionId") Long sessionId) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(case_id, step_id);
        if (apiKey == null || apiKey.isEmpty()) {
            return AjaxResult.error(HttpStatus.NOT_FOUND.value(), "API key not found");
        }
        String url = difyApiUrl + "/parameters";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        org.springframework.util.StopWatch stopWatch = new org.springframework.util.StopWatch();
        stopWatch.start();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        stopWatch.stop();
        log.info("调用Dify /parameters接口耗时: {} ms", stopWatch.getTotalTimeMillis());
        log.info("Dify parameters response: {}", response.getBody());

        String openingStatement = null;
        try {
            JSONObject root = JSON.parseObject(response.getBody());
            if (root != null) {
                if (root.containsKey("opening_statement")) {
                    openingStatement = root.getString("opening_statement");
                }
                if ((openingStatement == null || openingStatement.isEmpty()) && root.getJSONObject("data") != null) {
                    JSONObject data = root.getJSONObject("data");
                    if (data.containsKey("opening_statement")) {
                        openingStatement = data.getString("opening_statement");
                    }
                }
                if ((openingStatement == null || openingStatement.isEmpty()) && root.getJSONObject("config") != null) {
                    JSONObject config = root.getJSONObject("config");
                    if (config.containsKey("opening_statement")) {
                        openingStatement = config.getString("opening_statement");
                    }
                }
                if ((openingStatement == null || openingStatement.isEmpty()) && root.getJSONObject("parameters") != null) {
                    JSONObject params = root.getJSONObject("parameters");
                    if (params.containsKey("opening_statement")) {
                        openingStatement = params.getString("opening_statement");
                    }
                }
            }
        } catch (Exception e) {
            log.warn("解析开场白失败: {}", e.getMessage());
        }

        // 若提供了 sessionId 且成功解析到开场白，则保存到 biz_consultation_se_messages
        if (sessionId != null && openingStatement != null && !openingStatement.isEmpty()) {
            try {
                BizConsultationSeMessages message = getBizConsultationSeMessages(sessionId, openingStatement, stopWatch);
                int rows = bizConsultationSeMessagesService.insertBizConsultationSeMessages(message);
                log.info("开场白已保存，sessionId={}, rows={}", sessionId, rows);
            } catch (Exception e) {
                log.warn("保存开场白失败，sessionId={}, error= {}", sessionId, e.getMessage());
                return AjaxResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "开场白保存失败: " + e.getMessage());
            }
        }

        // 若未获取到开场白，返回体中增加提示
        if (openingStatement == null || openingStatement.isEmpty()) {
            JSONObject result = new JSONObject();
            result.put("msg", "未获取到开场白");
            result.put("raw", response.getBody());
            return new AjaxResult(response.getStatusCodeValue(), result.toJSONString());
        }

        return new AjaxResult(response.getStatusCodeValue(), response.getBody());
    }

    private static BizConsultationSeMessages getBizConsultationSeMessages(Long sessionId, String openingStatement, StopWatch stopWatch) {

        BizConsultationSeMessages message = new BizConsultationSeMessages();
        Date now = new Date();
        message.setSessionId(String.valueOf(sessionId));
        message.setMessageOrder("1"); // 开场第一条
        message.setSenderType(1L); // 1:患者（模拟患者）
        message.setMessageContent(openingStatement);
        message.setMessageType("text");
        message.setCreateBy(SecurityUtils.getUsername());
        message.setCreateTime(now);
        if (stopWatch != null) {
            message.setResponseTime(String.valueOf(stopWatch.getTotalTimeMillis()));
        }
        return message;
    }

    private int getNextMessageOrder(Long sessionId) {
        BizConsultationSeMessages filter = new BizConsultationSeMessages();
        filter.setSessionId(String.valueOf(sessionId));
        List<BizConsultationSeMessages> list = bizConsultationSeMessagesService.selectBizConsultationSeMessagesList(filter);
        int max = 0;
        if (list != null) {
            for (BizConsultationSeMessages m : list) {
                try {
                    if (m.getMessageOrder() != null) {
                        int v = Integer.parseInt(m.getMessageOrder());
                        if (v > max) max = v;
                    }
                } catch (NumberFormatException ignore) {
                }
            }
        }
        return max + 1;
    }
}
