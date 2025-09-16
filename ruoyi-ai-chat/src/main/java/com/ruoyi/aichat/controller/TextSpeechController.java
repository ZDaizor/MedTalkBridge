package com.ruoyi.aichat.controller;

import com.ruoyi.aichat.config.AsrConfig;
import com.ruoyi.aichat.dto.AsrRequest;
import com.ruoyi.aichat.dto.QueryResponse;
import com.ruoyi.aichat.service.AsrService;
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

import java.util.Base64;

@Api(tags = "文字语音互转接口")
@RestController
@RequestMapping("/text-speech")
public class TextSpeechController {
    private static final Logger logger = LoggerFactory.getLogger(TextSpeechController.class);

    private final AsrService asrService;
    private final AsrConfig asrConfig;

    @Autowired
    public TextSpeechController(AsrService asrService, AsrConfig asrConfig) {
        this.asrService = asrService;
        this.asrConfig = asrConfig;
    }

    @ApiOperation("提交语音识别任务(标准版异步)")
    @PostMapping("/submit")
    public ResponseEntity<String> submitTask(@RequestBody AsrRequest request) {
        try {
            String taskId = asrService.submitTask(request);
            return ResponseEntity.ok(taskId);
        } catch (Exception e) {
            logger.error("提交语音识别任务失败", e);
            return ResponseEntity.badRequest().body("提交失败: " + e.getMessage());
        }
    }

    @ApiOperation("查询识别结果")
    @GetMapping("/query/{taskId}")
    public ResponseEntity<QueryResponse> queryResult(@PathVariable String taskId) {
        try {
            QueryResponse result = asrService.queryResult(taskId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("查询识别结果失败", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation("同步提交并等待结果(标准版轮询)")
    @PostMapping("/sync")
    public ResponseEntity<QueryResponse> submitAndWait(@RequestBody AsrRequest request) {
        try {
            QueryResponse result = asrService.submitAndWait(request, 30, 2000);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("同步提交并等待结果失败", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation("极速识别(JSON 方式，audio.url 或 audio.data 二选一)")
    @PostMapping(value = "/flash", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QueryResponse> flashRecognizeJson(@RequestBody AsrRequest request) {
        try {
            if (request.getUser() == null) {
                AsrRequest.User u = new AsrRequest.User();
                u.setUid(asrConfig.getAppKey());
                request.setUser(u);
            }
            QueryResponse result = asrService.flashRecognize(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("极速识别(JSON)失败", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation("极速识别-文件上传(wav)，直接返回结果")
    @PostMapping(value = "/flash", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QueryResponse> flashRecognizeFile(
            @ApiParam("wav音频文件") @RequestPart("file") MultipartFile file,
            @ApiParam("是否开启标点") @RequestParam(value = "enablePunc", required = false, defaultValue = "true") boolean enablePunc,
            @ApiParam("是否展示分句") @RequestParam(value = "showUtterances", required = false, defaultValue = "true") boolean showUtterances
    ) {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            String originalName = file.getOriginalFilename();
            if (originalName == null || !originalName.toLowerCase().endsWith(".wav")) {
                return ResponseEntity.badRequest().body(null);
            }
            long size = file.getSize();
            // 100MB 限制
            if (size > 100L * 1024 * 1024) {
                return ResponseEntity.badRequest().body(null);
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
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("极速识别(文件)失败", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
