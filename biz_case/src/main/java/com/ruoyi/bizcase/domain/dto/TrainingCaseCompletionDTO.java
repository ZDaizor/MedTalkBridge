package com.ruoyi.bizcase.domain.dto;

public class TrainingCaseCompletionDTO {
    private int finashCase;
    private int totalCase;
    private double percentage;

    public int getFinashCase() {
        return finashCase;
    }
    public void setFinashCase(int finashCase) {
        this.finashCase = finashCase;
    }
    public int getTotalCase() {
        return totalCase;
    }
    public void setTotalCase(int totalCase) {
        this.totalCase = totalCase;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}

