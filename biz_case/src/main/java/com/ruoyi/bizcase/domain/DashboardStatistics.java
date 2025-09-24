package com.ruoyi.bizcase.domain;

/**
 * 仪表板统计信息实体
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
public class DashboardStatistics
{
    /** 总学生数 */
    private Long totalStudents;

    /** 平均成绩 */
    private Double averageScore;

    /** 总学习时长(分钟) */
    private Long totalLearningDuration;

    /** 总考试次数 */
    private Long totalExamCount;

    public DashboardStatistics() {
    }

    public DashboardStatistics(Long totalStudents, Double averageScore, Long totalLearningDuration, Long totalExamCount) {
        this.totalStudents = totalStudents;
        this.averageScore = averageScore;
        this.totalLearningDuration = totalLearningDuration;
        this.totalExamCount = totalExamCount;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Long getTotalLearningDuration() {
        return totalLearningDuration;
    }

    public void setTotalLearningDuration(Long totalLearningDuration) {
        this.totalLearningDuration = totalLearningDuration;
    }

    public Long getTotalExamCount() {
        return totalExamCount;
    }

    public void setTotalExamCount(Long totalExamCount) {
        this.totalExamCount = totalExamCount;
    }

    @Override
    public String toString() {
        return "DashboardStatistics{" +
                "totalStudents=" + totalStudents +
                ", averageScore=" + averageScore +
                ", totalLearningDuration=" + totalLearningDuration +
                ", totalExamCount=" + totalExamCount +
                '}';
    }
}