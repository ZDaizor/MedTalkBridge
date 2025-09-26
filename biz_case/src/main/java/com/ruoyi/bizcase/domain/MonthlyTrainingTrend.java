package com.ruoyi.bizcase.domain;

/**
 * 月度训练趋势实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class MonthlyTrainingTrend {
    
    /** 月份 */
    private String month;
    
    /** 训练次数 */
    private Long trainingCount;
    
    /** 考试次数 */
    private Long examCount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(Long trainingCount) {
        this.trainingCount = trainingCount;
    }

    public Long getExamCount() {
        return examCount;
    }

    public void setExamCount(Long examCount) {
        this.examCount = examCount;
    }
}