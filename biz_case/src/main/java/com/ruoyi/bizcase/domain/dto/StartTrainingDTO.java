package com.ruoyi.bizcase.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("开始训练参数")
public class StartTrainingDTO {
    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("病例ID")
    private String caseId;

    @ApiModelProperty("步骤ID")
    private Long stepId;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getCaseId() { return caseId; }
    public void setCaseId(String caseId) { this.caseId = caseId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
}

