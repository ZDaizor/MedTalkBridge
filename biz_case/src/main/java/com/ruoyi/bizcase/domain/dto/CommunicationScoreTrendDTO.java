package com.ruoyi.bizcase.domain.dto;

public class CommunicationScoreTrendDTO {
    private String monthDisplay;
    private Integer stepId;
    private String stepName;
    private Double score;

    public String getMonthDisplay() {
        return monthDisplay;
    }
    public void setMonthDisplay(String monthDisplay) {
        this.monthDisplay = monthDisplay;
    }
    public Integer getStepId() {
        return stepId;
    }
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }
    public String getStepName() {
        return stepName;
    }
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
}

