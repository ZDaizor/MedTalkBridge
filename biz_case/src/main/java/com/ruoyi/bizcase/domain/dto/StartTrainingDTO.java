package com.ruoyi.bizcase.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("开始训练参数")
public class StartTrainingDTO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("病例ID")
    private Long caseId;

    @ApiModelProperty("步骤ID")
    private Integer stepId;

    @ApiModelProperty("模式（1：训练，2：考试）")
    private Integer evalMode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getEvalMode() {
        return evalMode;
    }

    public void setEvalMode(Integer evalMode) {
        this.evalMode = evalMode;
    }
}
