package com.ruoyi.bizcase.domain;

import java.math.BigDecimal;

/**
 * 部门学生统计信息实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class DeptStudentStatistics {
    
    /** 部门名称 */
    private String departmentName;
    
    /** 总学生数 */
    private Long totalStudents;
    
    /** 学生姓名列表 */
    private String studentNames;
    
    /** 平均成绩 */
    private BigDecimal averageScore;
    
    /** 总学习时长（秒） */
    private Long totalStudyDurationSeconds;
    
    /** 总考试次数 */
    private Long totalExamAttempts;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentNames) {
        this.studentNames = studentNames;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public Long getTotalStudyDurationSeconds() {
        return totalStudyDurationSeconds;
    }

    public void setTotalStudyDurationSeconds(Long totalStudyDurationSeconds) {
        this.totalStudyDurationSeconds = totalStudyDurationSeconds;
    }

    public Long getTotalExamAttempts() {
        return totalExamAttempts;
    }

    public void setTotalExamAttempts(Long totalExamAttempts) {
        this.totalExamAttempts = totalExamAttempts;
    }
}