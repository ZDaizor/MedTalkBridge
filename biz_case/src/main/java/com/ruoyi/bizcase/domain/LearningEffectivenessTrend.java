package com.ruoyi.bizcase.domain;

/**
 * 学习效果趋势实体
 * 
 * @author ruoyi
 * @date 2025-09-27
 */
public class LearningEffectivenessTrend {
    
    /** 月份 */
    private String month;
    
    /** 平均分数 */
    private Double avgScore;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }
}