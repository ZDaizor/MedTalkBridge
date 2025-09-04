package com.ruoyi.aichat.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * AI对话配置类
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Configuration
@EnableConfigurationProperties(AiChatProperties.class)
public class AiChatConfig {

    private final AiChatProperties aiChatProperties;

    public AiChatConfig(AiChatProperties aiChatProperties) {
        this.aiChatProperties = aiChatProperties;
    }

    /**
     * 配置WebClient用于HTTP请求
     */
    @Bean("aiChatWebClient")
    public WebClient aiChatWebClient() {
        return WebClient.builder()
                .baseUrl(aiChatProperties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + aiChatProperties.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    /**
     * 获取AI配置属性
     */
    @Bean
    public AiChatProperties aiChatProperties() {
        return aiChatProperties;
    }
}
