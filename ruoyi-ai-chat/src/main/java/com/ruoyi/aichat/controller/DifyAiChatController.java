package com.ruoyi.aichat.controller;

import com.ruoyi.bizcase.service.IBizCasePromptService;
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

import java.util.*;

@Api(tags = "Dify AI 对话接口")
@RestController
@RequestMapping("/dify")
public class DifyAiChatController {
    private static final Logger log = LoggerFactory.getLogger(DifyAiChatController.class);
    @Value("${dify.api.url}")
    private String difyApiUrl;

    private String difyApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IBizCasePromptService bizCasePromptService;


    @ApiOperation(value = "AI对话接口", notes = "与 Dify AI 进行对话，返回 AI 回复内容")
    @PostMapping("/chat")
    public ResponseEntity<String> chat(
            @ApiParam(value = "用户输入问题", required = true) @RequestParam String query,
            @ApiParam(value = "会话ID，可选") @RequestParam(required = false) String conversationId,
            @ApiParam(value = "用户标识", required = true) @RequestParam String user,
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long caseId,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long stepId) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(caseId, stepId);
        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("API key not found");
        }
        String url = difyApiUrl + "/chat-messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("inputs", new HashMap<>());
        body.put("query", query);
        body.put("response_mode", "streaming");
        body.put("conversation_id", conversationId == null ? "" : conversationId);
        body.put("user", user);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    /**
     * 获取参数列表
     */
    @ApiOperation(value = "获取 Dify 参数列表", notes = "根据 caseId 和 stepId 获取 Dify 参数列表")
    @GetMapping("/parameters")
    public ResponseEntity<String> getParameters(
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long case_id,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long step_id) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(case_id, step_id);
        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("API key not found");
        }
        String url = difyApiUrl + "/parameters";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        log.info("Dify parameters response: {}", response.getBody());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    /**
     * 文字转语音接口
     */
    @ApiOperation(value = "文字转语音", notes = "将文本转换为语音，返回音频流")
    @PostMapping("/text-to-audio")
    public ResponseEntity<byte[]> textToAudio(
            @ApiParam(value = "要转换的文本", required = true) @RequestParam String text,
            @ApiParam(value = "用户标识", required = true) @RequestParam String user,
            @ApiParam(value = "消息ID", required = true) @RequestParam("messageId") String messageId,
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long caseId,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long stepId) {
        String apiKey = bizCasePromptService.getPromptKeyByCaseIdAndStepId(caseId, stepId);
        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        String url = difyApiUrl + "/text-to-audio";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(apiKey);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("text", text);
        formData.add("user", user);
        formData.add("message_id", messageId);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity(url, requestEntity, byte[].class);
        HttpHeaders responseHeaders = new HttpHeaders();
        MediaType contentType = response.getHeaders().getContentType();
        if (contentType != null) {
            responseHeaders.setContentType(contentType);
        } else {
            responseHeaders.setContentType(MediaType.valueOf("audio/wav"));
        }
        responseHeaders.setContentLength(response.getBody() != null ? response.getBody().length : 0);
        return new ResponseEntity<>(response.getBody(), responseHeaders, response.getStatusCode());
    }
}
