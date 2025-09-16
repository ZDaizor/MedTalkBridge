package com.ruoyi.aichat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "asr")
public class AsrConfig {
    // 基础认证配置
    private String appKey;
    private String accessKey;

    // 标准版资源与接口
    private String resourceId;   // 如 volc.bigasr.auc
    private String submitUrl;    // 标准版提交地址
    private String queryUrl;     // 标准版查询地址

    // 极速版配置
    private String flashUrl;         // 极速版一次返回接口地址 https://openspeech.bytedance.com/api/v3/auc/bigmodel/recognize/flash
    private String turboResourceId;  // 极速版资源ID 例如 volc.bigasr.auc_turbo

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getFlashUrl() {
        return flashUrl;
    }

    public void setFlashUrl(String flashUrl) {
        this.flashUrl = flashUrl;
    }

    public String getTurboResourceId() {
        return turboResourceId;
    }

    public void setTurboResourceId(String turboResourceId) {
        this.turboResourceId = turboResourceId;
    }
}
