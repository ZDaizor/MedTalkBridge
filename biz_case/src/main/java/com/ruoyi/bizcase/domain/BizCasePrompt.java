package com.ruoyi.bizcase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 biz_case_prompt
 *
 * @author daizor
 * @date 2025-09-04
 */
public class BizCasePrompt extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long promptId;

    /**
     * 提示词类型（1：内容；2：整体；3：病志书写）
     */
    @Excel(name = "提示词类型", readConverterExp = "1=：内容；2：整体；3：病志书写")
    private Long promptType;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long caseId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long stepId;

    @Excel(name = "提示词会话标识")
    private String promptKeyMetting;

    private String promptKeyScore;

    /***
     * 步骤分数占总体的百分比
     */
    @Excel(name = "步骤分数占总体的百分比")
    private java.math.BigDecimal promptPercentage;

    /**
     * 静态url
     */
    @Excel(name = "静态url")
    private String promptStaticUrl;

    /**
     * 动态 url
     */
    @Excel(name = "动态 url")
    private String promptDynamicUrl;

    public void setPromptId(Long promptId) {
        this.promptId = promptId;
    }

    public Long getPromptId() {
        return promptId;
    }

    public void setPromptType(Long promptType) {
        this.promptType = promptType;
    }

    public Long getPromptType() {
        return promptType;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Long getStepId() {
        return stepId;
    }

    public String getPromptKey() {
        return promptKeyMetting;
    }

    public void setPromptKey(String promptKeyMetting) {
        this.promptKeyMetting = promptKeyMetting;
    }


    public String getPromptKeyScore() {
        return promptKeyScore;
    }

    public void setPromptKeyScore(String promptKeyScore) {
        this.promptKeyScore = promptKeyScore;
    }

    public java.math.BigDecimal getPromptPercentage() {
        return promptPercentage;
    }

    public void setPromptPercentage(java.math.BigDecimal promptPercentage) {
        this.promptPercentage = promptPercentage;
    }

    public String getPromptStaticUrl() {
        return promptStaticUrl;
    }

    public void setPromptStaticUrl(String promptStaticUrl) {
        this.promptStaticUrl = promptStaticUrl;
    }

    public String getPromptDynamicUrl() {
        return promptDynamicUrl;
    }

    public void setPromptDynamicUrl(String promptDynamicUrl) {
        this.promptDynamicUrl = promptDynamicUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("promptId", getPromptId())
                .append("promptType", getPromptType())
                .append("caseId", getCaseId())
                .append("stepId", getStepId())
                .append("promptKeyMetting", getPromptKey())
                .append("promptKeyScore", getPromptKeyScore())
                .append("promptPercentage", getPromptPercentage())
                .append("promptStaticUrl", getPromptStaticUrl())
                .append("promptDynamicUrl", getPromptDynamicUrl())
                .toString();
    }
}
