package com.ruoyi.bizcase.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class UserAchievementDTO {
    private Long achievementId;
    private String achievementName;
    private String achievementContent;
    private String completionStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completionTime;

    public Long getAchievementId() {
        return achievementId;
    }
    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }
    public String getAchievementName() {
        return achievementName;
    }
    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }
    public String getAchievementContent() {
        return achievementContent;
    }
    public void setAchievementContent(String achievementContent) {
        this.achievementContent = achievementContent;
    }
    public String getCompletionStatus() {
        return completionStatus;
    }
    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }
    public LocalDateTime getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }
}

