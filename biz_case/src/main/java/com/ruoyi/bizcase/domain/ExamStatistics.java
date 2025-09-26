package com.ruoyi.bizcase.domain;

/**
 * 考试统计实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class ExamStatistics {
    
    /** 统计类别标识 */
    private Integer categoryId;
    
    /** 数量 */
    private Long count;
    
    /** 百分比 */
    private String percentage;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}