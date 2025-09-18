package com.ruoyi.aichat.controller;

import com.ruoyi.bizcase.service.IBizCasePromptService;
import com.ruoyi.common.core.domain.AjaxResult;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.Base64;

@Api(tags = "Dify AI 对话接口")
@RestController
@RequestMapping("/dify")
public class DifyAiChatController {
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


    @ApiOperation(value = "AI对话接口", notes = "与 Dify AI 进行对话，返回 AI 回复内容")
    @PostMapping("/chat")
    public AjaxResult chat(
            @ApiParam(value = "用户输入问题", required = true) @RequestParam String query,
            @ApiParam(value = "会话ID，可选") @RequestParam(required = false) String conversationId,
            @ApiParam(value = "用户标识", required = true) @RequestParam String user,
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long caseId,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long stepId) {
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
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return new AjaxResult(response.getStatusCodeValue(), response.getBody());
    }

    /**
     * 获取参数列表
     */
    @ApiOperation(value = "获取 Dify 参数列表", notes = "根据 caseId 和 stepId 获取 Dify 参数列表")
    @GetMapping("/parameters")
    public AjaxResult getParameters(
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long case_id,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long step_id) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(case_id, step_id);
        if (apiKey == null || apiKey.isEmpty()) {
            return AjaxResult.error(HttpStatus.NOT_FOUND.value(), "API key not found");
        }
        String url = difyApiUrl + "/parameters";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        log.info("Dify parameters response: {}", response.getBody());
        return new AjaxResult(response.getStatusCodeValue(), response.getBody());
    }
}

