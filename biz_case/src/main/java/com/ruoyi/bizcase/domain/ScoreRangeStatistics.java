package com.ruoyi.bizcase.domain;

/**
 * 分数范围统计实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class ScoreRangeStatistics {
    
    /** 分数范围 */
    private String scoreRange;
    
    /** 学生数量 */
    private Long studentCount;

    public String getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(String scoreRange) {
        this.scoreRange = scoreRange;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }
}