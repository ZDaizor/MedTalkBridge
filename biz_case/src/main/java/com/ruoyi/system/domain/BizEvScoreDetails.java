package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内容得分详情对象 biz_ev_score_details
 * 
 * @author daizor
 * @date 2025-09-04
 */
public class BizEvScoreDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long scoreId;

    /** 主表 */
    @Excel(name = "主表")
    private Long evaluationId;

    /** 分项 id，在分项表中 */
    @Excel(name = "分项 id，在分项表中")
    private Long itemId;

    /** 实际得分 */
    @Excel(name = "实际得分")
    private BigDecimal scoreAchieved;

    /** 该项总分 */
    @Excel(name = "该项总分")
    private BigDecimal scoreMax;

    /** 分数类型（1：内容，2：整体） */
    @Excel(name = "分数类型", readConverterExp = "1=：内容，2：整体")
    private Long scoreType;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }

    public void setEvaluationId(Long evaluationId) 
    {
        this.evaluationId = evaluationId;
    }

    public Long getEvaluationId() 
    {
        return evaluationId;
    }

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }

    public void setScoreAchieved(BigDecimal scoreAchieved) 
    {
        this.scoreAchieved = scoreAchieved;
    }

    public BigDecimal getScoreAchieved() 
    {
        return scoreAchieved;
    }

    public void setScoreMax(BigDecimal scoreMax) 
    {
        this.scoreMax = scoreMax;
    }

    public BigDecimal getScoreMax() 
    {
        return scoreMax;
    }

    public void setScoreType(Long scoreType) 
    {
        this.scoreType = scoreType;
    }

    public Long getScoreType() 
    {
        return scoreType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scoreId", getScoreId())
            .append("evaluationId", getEvaluationId())
            .append("itemId", getItemId())
            .append("scoreAchieved", getScoreAchieved())
            .append("scoreMax", getScoreMax())
            .append("scoreType", getScoreType())
            .toString();
    }
}
