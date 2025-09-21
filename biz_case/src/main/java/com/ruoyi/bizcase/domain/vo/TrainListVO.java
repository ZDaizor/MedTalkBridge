package com.ruoyi.bizcase.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class TrainListVO implements Serializable {
    private Long recordId;

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    private String nickName;
    private String caseName;
    private String stepName;
    private String score;
    private String level;
    private Date startTime;
    private Integer totle;
    private Integer stepOrder;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Date endTime;

    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCaseName() { return caseName; }
    public void setCaseName(String caseName) { this.caseName = caseName; }

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }

    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Integer getTotle() { return totle; }
    public void setTotle(Integer totle) { this.totle = totle; }
}

