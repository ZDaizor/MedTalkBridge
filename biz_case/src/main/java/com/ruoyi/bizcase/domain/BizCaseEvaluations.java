package com.ruoyi.bizcase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生病例得分总对象 biz_case_evaluations
 * 
 * @author daizor
 * @date 2025-09-04
 */
public class BizCaseEvaluations extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long evaluationId;

    /** 用户 id */
    @Excel(name = "用户 id")
    private Long userId;

    /** 病例 id */
    @Excel(name = "病例 id")
    private Long caseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long stepId;

    /** 整体得分 */
    @Excel(name = "整体得分")
    private Long totleScore;

    /** 整体满分 */
    @Excel(name = "整体满分")
    private Long totleMaxScore;

    /** 模式（1：训练，2：考试） */
    @Excel(name = "模式", readConverterExp = "1=：训练，2：考试")
    private Long evalMode;

    public void setEvaluationId(Long evaluationId) 
    {
        this.evaluationId = evaluationId;
    }

    public Long getEvaluationId() 
    {
        return evaluationId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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

    public void setTotleScore(Long totleScore) 
    {
        this.totleScore = totleScore;
    }

    public Long getTotleScore() 
    {
        return totleScore;
    }

    public void setTotleMaxScore(Long totleMaxScore) 
    {
        this.totleMaxScore = totleMaxScore;
    }

    public Long getTotleMaxScore() 
    {
        return totleMaxScore;
    }

    public void setEvalMode(Long evalMode) 
    {
        this.evalMode = evalMode;
    }

    public Long getEvalMode() 
    {
        return evalMode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaluationId", getEvaluationId())
            .append("userId", getUserId())
            .append("caseId", getCaseId())
            .append("stepId", getStepId())
            .append("totleScore", getTotleScore())
            .append("totleMaxScore", getTotleMaxScore())
            .append("evalMode", getEvalMode())
            .toString();
    }
}
