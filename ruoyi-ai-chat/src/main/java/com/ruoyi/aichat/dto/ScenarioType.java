package com.ruoyi.aichat.dto;

/**
 * 场景类型枚举
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public enum ScenarioType {
    
    /**
     * 术前场景
     */
    PRE_OPERATION("PRE_OPERATION", "术前"),
    
    /**
     * 术中场景
     */
    DURING_OPERATION("DURING_OPERATION", "术中"),
    
    /**
     * 术后场景
     */
    POST_OPERATION("POST_OPERATION", "术后");

    private final String code;
    private final String description;

    ScenarioType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据代码获取场景类型
     * 
     * @param code 场景代码
     * @return 场景类型
     */
    public static ScenarioType fromCode(String code) {
        for (ScenarioType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的场景类型: " + code);
    }

    /**
     * 验证场景类型代码是否有效
     * 
     * @param code 场景代码
     * @return 是否有效
     */
    public static boolean isValid(String code) {
        try {
            fromCode(code);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
