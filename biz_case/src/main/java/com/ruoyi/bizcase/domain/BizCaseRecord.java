package com.ruoyi.bizcase.domain;

import java.io.Serializable;

public class BizCaseRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Integer evalMode;
    // ... 其他字段 ...

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getEvalMode() { return evalMode; }
    public void setEvalMode(Integer evalMode) { this.evalMode = evalMode; }

    // ... 其他getter/setter ...
}

