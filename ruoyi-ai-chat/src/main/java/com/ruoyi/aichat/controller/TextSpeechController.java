package com.ruoyi.aichat.controller;

import com.ruoyi.aichat.config.AsrConfig;
import com.ruoyi.aichat.dto.AsrRequest;
import com.ruoyi.aichat.dto.QueryResponse;
import com.ruoyi.aichat.service.AsrService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.server.UID;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "文字语音互转接口")
@RestController
@RequestMapping("/text-speech")
public class TextSpeechController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TextSpeechController.class);

    private final AsrService asrService;
    private final AsrConfig asrConfig;

    @Autowired
    public TextSpeechController(AsrService asrService, AsrConfig asrConfig) {
        this.asrService = asrService;
        this.asrConfig = asrConfig;
    }

    /**
     * 语音转文字
     *
     * @param file
     * @param enablePunc
     * @param showUtterances
     * @return
     */
    @ApiOperation("极速识别-文件上传(wav)，直接返回结果")
    @PostMapping(value = "/flash", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult flashRecognizeFile(@ApiParam("wav音频文件") @RequestPart("file") MultipartFile file, @ApiParam(
                                                 "是否开启标点") @RequestParam(value = "enablePunc", required = false,
                                                 defaultValue = "true") boolean enablePunc
            , @ApiParam("是否展示分句") @RequestParam(value = "showUtterances", required = false, defaultValue = "true") boolean showUtterances) {
        try {
            if (file == null || file.isEmpty()) {
                return AjaxResult.error();
            }
            String originalName = file.getOriginalFilename();
            if (originalName == null || !originalName.toLowerCase().endsWith(".wav")) {
                return AjaxResult.error();
            }
            long size = file.getSize();
            // 100MB 限制
            if (size > 100L * 1024 * 1024) {
                return AjaxResult.error();
            }
            byte[] bytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            AsrRequest req = new AsrRequest();
            AsrRequest.User user = new AsrRequest.User();
            user.setUid(asrConfig.getAppKey());
            req.setUser(user);

            AsrRequest.Audio audio = new AsrRequest.Audio();
            audio.setFormat("wav");
            audio.setData(base64);
            req.setAudio(audio);

            AsrRequest.Request r = new AsrRequest.Request();
            r.setModelName("bigmodel");
            r.setEnablePunc(enablePunc);
            r.setShowUtterances(showUtterances);
            req.setRequest(r);

            QueryResponse result = asrService.flashRecognize(req);
            return AjaxResult.success(result);
        } catch (Exception e) {
            logger.error("极速识别(文件)失败", e);
            return AjaxResult.error();
        }
    }

    /**
     * 文字转语音()
     */
    @PostMapping("/tts")
    @ApiOperation("文字转语音")
    public AjaxResult textToSpeech(
            @ApiParam(value = "待合成文本", required = true) @RequestParam String text,
            @ApiParam("音色类型") @RequestParam(defaultValue = "BV700_streaming") String voiceType,
            @ApiParam("编码格式") @RequestParam(defaultValue = "wav") String encoding,
            @ApiParam("采样率") @RequestParam(defaultValue = "24000") int rate,
            @ApiParam(value = "用户标识", required = true) @RequestParam String uid
    ) {
        try {
            // 构造请求体
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> app = new HashMap<>();
            app.put("appid", asrConfig.getAppKey());
            app.put("cluster", "volcano_tts");
            requestBody.put("app", app);

            Map<String, Object> user = new HashMap<>();
            user.put("uid", uid);
            requestBody.put("user", user);

            Map<String, Object> audio = new HashMap<>();
            audio.put("voice_type", voiceType);
            audio.put("encoding", encoding);
            audio.put("compression_rate", 1);
            audio.put("rate", rate);
            audio.put("speed_ratio", 1.0);
            audio.put("volume_ratio", 1.0);
            audio.put("pitch_ratio", 1.0);
            audio.put("emotion", "happy");
            audio.put("language", "cn");
            requestBody.put("audio", audio);

            Map<String, Object> req = new HashMap<>();
            req.put("reqid", java.util.UUID.randomUUID().toString());
            req.put("text", text);
            req.put("text_type", "plain");
            req.put("operation", "query");
            req.put("silence_duration", "125");
            req.put("with_frontend", "1");
            req.put("frontend_type", "unitTson");
            req.put("pure_english_opt", "1");
            req.put("extra_param", "{\"disable_emoji_filter\":true}");
            requestBody.put("request", req);

            // 发送请求
            org.springframework.web.client.RestTemplate restTemplate =
                    new org.springframework.web.client.RestTemplate();
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer;" + asrConfig.getAccessKey());
            org.springframework.http.HttpEntity<Map<String, Object>> entity =
                    new org.springframework.http.HttpEntity<>(requestBody, headers);

            String url = asrConfig.getTtsUrl();
            org.springframework.http.ResponseEntity<String> response = restTemplate.postForEntity(url, entity,
                    String.class);
            logger.info("文字转语音返回: {}", response.getBody());
            // 解析返回的数据，转换为 RuoYi 通用结构
            JSONObject ttsResponse = JSON.parseObject(response.getBody());
            return AjaxResult.success(ttsResponse);
        } catch (Exception e) {
            logger.error("文字转语音失败", e);
            return AjaxResult.error();
        }
    }

}
