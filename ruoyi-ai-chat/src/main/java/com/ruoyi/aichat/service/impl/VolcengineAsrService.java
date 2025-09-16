package com.ruoyi.aichat.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ruoyi.aichat.config.AsrConfig;
import com.ruoyi.aichat.dto.AsrRequest;
import com.ruoyi.aichat.dto.QueryResponse;
import com.ruoyi.aichat.service.AsrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VolcengineAsrService implements AsrService {

    private static final Logger logger = LoggerFactory.getLogger(VolcengineAsrService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private final AsrConfig asrConfig;
    @Autowired
    private RestTemplate restTemplate;

    public VolcengineAsrService(AsrConfig asrConfig) {
        this.asrConfig = asrConfig;
    }

    private ResponseEntity<String> postWithHeaders(String url, String body, Map<String, String> headersMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    @Override
    public String submitTask(AsrRequest request) {
        try {
            String requestBody = objectMapper.writeValueAsString(request);
            String requestId = java.util.UUID.randomUUID().toString();

            Map<String, String> headers = new HashMap<>();
            headers.put("X-Api-App-Key", asrConfig.getAppKey());
            headers.put("X-Api-Access-Key", asrConfig.getAccessKey());
            headers.put("X-Api-Resource-Id", asrConfig.getResourceId());
            headers.put("X-Api-Request-Id", requestId);
            headers.put("X-Api-Sequence", "-1");

            ResponseEntity<String> response = postWithHeaders(asrConfig.getSubmitUrl(), requestBody, headers);

            if (response.getStatusCode().is2xxSuccessful()) {
                String statusCode = response.getHeaders().getFirst("X-Api-Status-Code");
                if ("20000000".equals(statusCode)) {
                    logger.info("任务提交成功，任务ID: {}", requestId);
                    return requestId;
                } else {
                    String message = response.getHeaders().getFirst("X-Api-Message");
                    logger.error("任务提交失败，状态码: {}, 消息: {}", statusCode, message);
                    throw new RuntimeException("任务提交失败: " + message);
                }
            } else {
                throw new RuntimeException("HTTP请求失败: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("提交任务时发生错误", e);
            throw new RuntimeException("提交任务失败", e);
        }
    }

    @Override
    public QueryResponse queryResult(String taskId) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("X-Api-App-Key", asrConfig.getAppKey());
            headers.put("X-Api-Access-Key", asrConfig.getAccessKey());
            headers.put("X-Api-Resource-Id", asrConfig.getResourceId());
            headers.put("X-Api-Request-Id", taskId);

            ResponseEntity<String> response = postWithHeaders(asrConfig.getQueryUrl(), "{}", headers);

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                return objectMapper.readValue(responseBody, QueryResponse.class);
            } else {
                throw new RuntimeException("查询结果失败: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("查询结果时发生错误", e);
            throw new RuntimeException("查询结果失败", e);
        }
    }

    @Override
    public QueryResponse submitAndWait(AsrRequest request, int maxRetry, long interval) {
        String taskId = submitTask(request);
        logger.info("任务已提交，开始轮询结果，任务ID: {}", taskId);

        int retryCount = 0;
        while (retryCount < maxRetry) {
            try {
                QueryResponse result = queryResult(taskId);
                if (result != null && result.getResult() != null && result.getResult().getText() != null) {
                    logger.info("识别完成，任务ID: {}", taskId);
                    return result;
                }

                logger.info("任务处理中，第{}次重试，等待{}ms后重试", retryCount + 1, interval);
                Thread.sleep(interval);
                retryCount++;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("轮询��中断", e);
            } catch (Exception e) {
                logger.warn("第{}次查询失败: {}", retryCount + 1, e.getMessage());
                retryCount++;
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("轮询被中断", ie);
                }
            }
        }

        throw new RuntimeException("超过最大重试次数，任务可能仍在处理中");
    }

    @Override
    public QueryResponse flashRecognize(AsrRequest request) {
        try {
            if (request == null) {
                throw new IllegalArgumentException("请求不能为空");
            }
            if (request.getAudio() == null || (request.getAudio().getUrl() == null && request.getAudio().getData() == null)) {
                throw new IllegalArgumentException("audio.url 与 audio.data 必须二选一");
            }

            // 构建最小JSON结构
            ObjectNode root = objectMapper.createObjectNode();
            ObjectNode userNode = root.putObject("user");
            String uid = (request.getUser() != null && request.getUser().getUid() != null)
                    ? request.getUser().getUid() : asrConfig.getAppKey();
            userNode.put("uid", uid);

            ObjectNode audioNode = root.putObject("audio");
            if (request.getAudio().getUrl() != null) {
                audioNode.put("url", request.getAudio().getUrl());
            } else {
                audioNode.put("data", request.getAudio().getData());
            }

            ObjectNode reqNode = root.putObject("request");
            String modelName = (request.getRequest() != null && request.getRequest().getModelName() != null)
                    ? request.getRequest().getModelName() : "bigmodel";
            reqNode.put("model_name", modelName);

            String requestBody = objectMapper.writeValueAsString(root);
            String requestId = UUID.randomUUID().toString();

            Map<String, String> headers = new HashMap<>();
            headers.put("X-Api-App-Key", asrConfig.getAppKey());
            headers.put("X-Api-Access-Key", asrConfig.getAccessKey());
            headers.put("X-Api-Resource-Id", asrConfig.getTurboResourceId() != null ? asrConfig.getTurboResourceId() : "volc.bigasr.auc_turbo");
            headers.put("X-Api-Request-Id", requestId);
            headers.put("X-Api-Sequence", "-1");

            ResponseEntity<String> response = postWithHeaders(asrConfig.getFlashUrl(), requestBody, headers);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("HTTP请求失败:" + response.getStatusCode());
            }
            String statusCode = response.getHeaders().getFirst("X-Api-Status-Code");
            if (!"20000000".equals(statusCode)) {
                String message = response.getHeaders().getFirst("X-Api-Message");
                throw new RuntimeException("极速识别失败: " + statusCode + ", " + message);
            }
            String body = response.getBody();
            QueryResponse qr = objectMapper.readValue(body, QueryResponse.class);
            logger.info("flash识别成功 requestId={}, 文本={}", requestId, (qr!=null && qr.getResult()!=null)? qr.getResult().getText(): "");
            return qr;
        } catch (Exception e) {
            logger.error("flash极速识别失败", e);
            throw new RuntimeException("flash识别失败", e);
        }
    }
}