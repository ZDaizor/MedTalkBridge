package com.ruoyi.aichat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/dify")
public class DifyAiChatController {
    @Value("${dify.api.url}")
    private String difyApiUrl;

    @Value("${dify.api.key}")
    private String difyApiKey;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String query,
                                       @RequestParam(required = false) String conversationId,
                                       @RequestParam String user) {
        String url = difyApiUrl + "/chat-messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(difyApiKey);

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
}
