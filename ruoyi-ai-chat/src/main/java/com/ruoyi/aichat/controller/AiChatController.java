package com.ruoyi.aichat.controller;

import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import com.ruoyi.aichat.service.IAiChatService;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * AI对话控制器
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Api(tags = "AI对话管理")
@RestController
@RequestMapping("/ai/chat")
@Validated
public class AiChatController {

    private static final Logger logger = LoggerFactory.getLogger(AiChatController.class);

    private final IAiChatService aiChatService;

    public AiChatController(IAiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    /**
     * AI对话接口
     */
    @ApiOperation("AI对话")
    @PostMapping("/send")

    public AjaxResult chat(@Valid @RequestBody AiChatRequest request) {
        try {
            logger.info("收到AI对话请求: {}", request);
            
            AiChatResponse response = aiChatService.chat(request);
            
            if (response.getSuccess()) {
                logger.info("AI对话成功，会话ID: {}, 耗时: {}ms", 
                           response.getSessionId(), response.getProcessingTime());
                return AjaxResult.success("对话成功", response);
            } else {
                logger.warn("AI对话失败: {}", response.getErrorMessage());
                return AjaxResult.error(response.getErrorMessage());
            }
            
        } catch (Exception e) {
            logger.error("AI对话接口异常", e);
            return AjaxResult.error("AI对话服务异常: " + e.getMessage());
        }
    }
    /**
     * 健康检查接口
     */
    @ApiOperation("AI服务健康检查")
    @GetMapping("/health")
    public AjaxResult healthCheck() {
        try {
            boolean available = aiChatService.isServiceAvailable();
            Map<String, Object> result = new HashMap<>();
            result.put("available", available);
            result.put("status", available ? "UP" : "DOWN");
            result.put("timestamp", System.currentTimeMillis());
            
            if (available) {
                return AjaxResult.success("AI服务正常", result);
            } else {
                return AjaxResult.error("AI服务不可用", result);
            }
        } catch (Exception e) {
            logger.error("健康检查失败", e);
            return AjaxResult.error("健康检查失败: " + e.getMessage());
        }
    }

    /**
     * 批量对话接口（可选功能）
     */
    @ApiOperation("批量AI对话")
    @PostMapping("/batch")
    public AjaxResult batchChat(@Valid @RequestBody AiChatRequest[] requests) {
        try {
            if (requests == null || requests.length == 0) {
                return AjaxResult.error("请求列表不能为空");
            }
            
            if (requests.length > 10) {
                return AjaxResult.error("批量请求数量不能超过10个");
            }
            
            AiChatResponse[] responses = new AiChatResponse[requests.length];
            for (int i = 0; i < requests.length; i++) {
                responses[i] = aiChatService.chat(requests[i]);
            }
            
            return AjaxResult.success("批量对话完成", responses);
            
        } catch (Exception e) {
            logger.error("批量对话接口异常", e);
            return AjaxResult.error("批量对话服务异常: " + e.getMessage());
        }
    }
}
