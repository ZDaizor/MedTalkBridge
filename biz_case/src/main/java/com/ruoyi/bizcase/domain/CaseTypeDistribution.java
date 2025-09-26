package com.ruoyi.bizcase.domain;

/**
 * 病例类型分布实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class CaseTypeDistribution {
    
    /** 病例类型 */
    private String caseType;
    
    /** 完成次数 */
    private Long completedCount;
    
    /** 百分比 */
    private Double percentage;

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Long getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Long completedCount) {
        this.completedCount = completedCount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}