package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内容得分模板对象 biz_score_templates
 * 
 * @author daizor
 * @date 2025-09-03
 */
public class BizScoreTemplates extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long templatesId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long caseId;

    /** 步骤 id */
    @Excel(name = "步骤 id")
    private Long stepId;

    /** 分项名称 */
    @Excel(name = "分项名称")
    private String itemName;

    /** 默认满分 */
    @Excel(name = "默认满分")
    private BigDecimal defaultMaxScore;

    /** 显示顺序，用于前端排序 */
    @Excel(name = "显示顺序，用于前端排序")
    private Long displayOrder;

    /** 是否可用（0：不可用，1：可用） */
    @Excel(name = "是否可用", readConverterExp = "0=：不可用，1：可用")
    private Long isActive;

    /** 模板类型 （1：内容，2：整体） */
    @Excel(name = "模板类型 ", readConverterExp = "1=：内容，2：整体")
    private Long templateType;

    public void setTemplatesId(Long templatesId) 
    {
        this.templatesId = templatesId;
    }

    public Long getTemplatesId() 
    {
        return templatesId;
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

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setDefaultMaxScore(BigDecimal defaultMaxScore) 
    {
        this.defaultMaxScore = defaultMaxScore;
    }

    public BigDecimal getDefaultMaxScore() 
    {
        return defaultMaxScore;
    }

    public void setDisplayOrder(Long displayOrder) 
    {
        this.displayOrder = displayOrder;
    }

    public Long getDisplayOrder() 
    {
        return displayOrder;
    }

    public void setIsActive(Long isActive) 
    {
        this.isActive = isActive;
    }

    public Long getIsActive() 
    {
        return isActive;
    }

    public void setTemplateType(Long templateType) 
    {
        this.templateType = templateType;
    }

    public Long getTemplateType() 
    {
        return templateType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templatesId", getTemplatesId())
            .append("caseId", getCaseId())
            .append("stepId", getStepId())
            .append("itemName", getItemName())
            .append("defaultMaxScore", getDefaultMaxScore())
            .append("displayOrder", getDisplayOrder())
            .append("isActive", getIsActive())
            .append("templateType", getTemplateType())
            .toString();
    }
}
