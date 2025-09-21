package com.ruoyi.bizcase.domain.vo;

import java.io.Serializable;

public class TrainRecordVO implements Serializable {
    private Long recordId;
    private String nickName;
    private Integer takenTime;
    private Double aiScore;
    private String comments;

    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public Integer getTakenTime() { return takenTime; }
    public void setTakenTime(Integer takenTime) { this.takenTime = takenTime; }

    public Double getAiScore() { return aiScore; }
    public void setAiScore(Double aiScore) { this.aiScore = aiScore; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}

