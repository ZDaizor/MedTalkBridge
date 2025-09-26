package com.ruoyi.bizcase.domain;

/**
 * 活跃用户周统计实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class ActiveUsersWeeklyStats {
    
    /** 星期 */
    private String dayOfWeek;
    
    /** 星期编号 */
    private Integer dayNumber;
    
    /** 活跃用户数 */
    private Long activeUsers;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Long activeUsers) {
        this.activeUsers = activeUsers;
    }
}