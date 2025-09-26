package com.ruoyi.bizcase.domain;

/**
 * 阶段评估统计实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class StageAssessmentStatistics {
    
    /** 阶段名称 */
    private String stageName;
    
    /** 完成数量 */
    private Long completionCount;
    
    /** 平均分 */
    private Double averageScore;
    
    /** 通过率 */
    private String passRate;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Long getCompletionCount() {
        return completionCount;
    }

    public void setCompletionCount(Long completionCount) {
        this.completionCount = completionCount;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }
}