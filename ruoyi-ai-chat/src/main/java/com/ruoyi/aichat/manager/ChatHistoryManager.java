package com.ruoyi.aichat.manager;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对话历史管理器
 */
@Component
public class ChatHistoryManager {

    // 使用内存存储，生产环境建议使用Redis
    private final Map<String, List<ChatMessage>> sessionHistories = new ConcurrentHashMap<>();

    // 最大历史记录数，防止内存溢出
    private static final int MAX_HISTORY_SIZE = 20;

    /**
     * 添加消息到历史记录
     */
    public void addMessage(String sessionId, String role, String content) {
        List<ChatMessage> history = sessionHistories.computeIfAbsent(sessionId, k -> new ArrayList<>());
        history.add(new ChatMessage(role, content));

        // 限制历史记录大小，保留最近的消息
        if (history.size() > MAX_HISTORY_SIZE) {
            history.remove(0);
            history.remove(0); // 移除最早的一对问答
        }
    }

    /**
     * 获取会话历史
     */
    public List<ChatMessage> getHistory(String sessionId) {
        return sessionHistories.getOrDefault(sessionId, new ArrayList<>());
    }

    /**
     * 清除会话历史
     */
    public void clearHistory(String sessionId) {
        sessionHistories.remove(sessionId);
    }

    /**
     * 对话消息实体
     */
    public static class ChatMessage {
        private String role;
        private String content;
        private Long timestamp;

        public ChatMessage(String role, String content) {
            this.role = role;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
        }

        // getter和setter
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    }
}
