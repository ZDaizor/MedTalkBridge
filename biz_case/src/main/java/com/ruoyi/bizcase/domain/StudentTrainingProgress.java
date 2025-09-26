package com.ruoyi.bizcase.domain;

/**
 * 学生训练进度统计实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class StudentTrainingProgress {
    
    /** 进度类别标识 */
    private Integer progressCategoryId;
    
    /** 学生数量 */
    private Long studentCount;
    
    /** 百分比 */
    private String percentage;

    public Integer getProgressCategoryId() {
        return progressCategoryId;
    }

    public void setProgressCategoryId(Integer progressCategoryId) {
        this.progressCategoryId = progressCategoryId;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}