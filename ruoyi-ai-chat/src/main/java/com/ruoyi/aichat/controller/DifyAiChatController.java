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

    /**
     * 语音转文字接口（豆包语音识别）
     * 若未配置 doubao.api.key 则返回 501
     */
    @ApiOperation(value = "语音转文字", notes = "上传音频文件并识别成文本 (火山引擎豆包语音模型)")
    @PostMapping(value = "/audio-to-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> audioToText(
            @ApiParam(value = "音频文件", required = true) @RequestPart("file") MultipartFile file,
            @ApiParam(value = "用户标识", required = true) @RequestParam String user,
            @ApiParam(value = "业务case主键", required = true) @RequestParam("caseId") Long caseId,
            @ApiParam(value = "步骤主键", required = true) @RequestParam("stepId") Long stepId) {
        if (doubaoApiKey == null || doubaoApiKey.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Doubao API key not configured");
        }
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Audio file is empty");
        }
        try {
            String originalName = file.getOriginalFilename();
            String ext = "wav";
            if (originalName != null && originalName.contains(".")) {
                ext = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
            }
            byte[] data = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(data);

            String url = doubaoApiUrl + "/audio/transcriptions"; // 若基础URL已含 /api/v3

            Map<String, Object> audio = new HashMap<>();
            audio.put("format", ext);
            audio.put("content", base64);

            Map<String, Object> body = new HashMap<>();
            body.put("model", doubaoApiModel);
            body.put("audio", audio);
            body.put("user", user);
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("caseId", caseId);
            metadata.put("stepId", stepId);
            body.put("metadata", metadata);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(doubaoApiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            log.error("调用豆包语音识别失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Speech to text failed: " + e.getMessage());
        }
    }
}

