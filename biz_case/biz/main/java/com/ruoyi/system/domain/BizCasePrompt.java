package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 biz_case_prompt
 * 
 * @author daizor
 * @date 2025-09-03
 */
public class BizCasePrompt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long promptId;

    /** 提示词内容 */
    @Excel(name = "提示词内容")
    private String promptContent;

    /** 提示词类型（1：内容；2：整体；3：病志书写） */
    @Excel(name = "提示词类型", readConverterExp = "1=：内容；2：整体；3：病志书写")
    private Long promptType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long caseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long stepId;

    public void setPromptId(Long promptId) 
    {
        this.promptId = promptId;
    }

    public Long getPromptId() 
    {
        return promptId;
    }

    public void setPromptContent(String promptContent) 
    {
        this.promptContent = promptContent;
    }

    public String getPromptContent() 
    {
        return promptContent;
    }

    public void setPromptType(Long promptType) 
    {
        this.promptType = promptType;
    }

    public Long getPromptType() 
    {
        return promptType;
    }

    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
    }

    public void setStepId(Long stepId) 
    {
        this.stepId = stepId;
    }

    public Long getStepId() 
    {
        return stepId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("promptId", getPromptId())
            .append("promptContent", getPromptContent())
            .append("promptType", getPromptType())
            .append("caseId", getCaseId())
            .append("stepId", getStepId())
            .toString();
    }
}
