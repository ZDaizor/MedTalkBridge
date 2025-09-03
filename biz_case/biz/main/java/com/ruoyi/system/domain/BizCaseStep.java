package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 biz_case_step
 * 
 * @author daizor
 * @date 2025-09-03
 */
public class BizCaseStep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long stepId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long caseId;

    /** 步骤名称 */
    @Excel(name = "步骤名称")
    private String stepName;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long stepOrder;

    public void setStepId(Long stepId) 
    {
        this.stepId = stepId;
    }

    public Long getStepId() 
    {
        return stepId;
    }

    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
    }

    public void setStepName(String stepName) 
    {
        this.stepName = stepName;
    }

    public String getStepName() 
    {
        return stepName;
    }

    public void setStepOrder(Long stepOrder) 
    {
        this.stepOrder = stepOrder;
    }

    public Long getStepOrder() 
    {
        return stepOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stepId", getStepId())
            .append("caseId", getCaseId())
            .append("stepName", getStepName())
            .append("stepOrder", getStepOrder())
            .toString();
    }
}
