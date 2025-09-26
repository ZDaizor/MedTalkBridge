package com.ruoyi.bizcase.domain;

/**
 * 教学数据总体概览实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class TeachingDataOverview {
    
    /** 总学生数 */
    private Long totalStudents;
    
    /** 活跃学生数 */
    private Long activeStudents;
    
    /** 总完成训练次数 */
    private Long totalCompletedTrainings;
    
    /** 平均得分 */
    private Double averageScore;
    
    /** 总学习时长 */
    private String totalLearningDuration;
    
    /** 通过率 */
    private String passRate;

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getActiveStudents() {
        return activeStudents;
    }

    public void setActiveStudents(Long activeStudents) {
        this.activeStudents = activeStudents;
    }

    public Long getTotalCompletedTrainings() {
        return totalCompletedTrainings;
    }

    public void setTotalCompletedTrainings(Long totalCompletedTrainings) {
        this.totalCompletedTrainings = totalCompletedTrainings;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public String getTotalLearningDuration() {
        return totalLearningDuration;
    }

    public void setTotalLearningDuration(String totalLearningDuration) {
        this.totalLearningDuration = totalLearningDuration;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }
}