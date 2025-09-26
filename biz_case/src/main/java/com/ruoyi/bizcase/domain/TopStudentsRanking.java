package com.ruoyi.bizcase.domain;

/**
 * 优秀学生排行榜实体
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class TopStudentsRanking {
    
    /** 排名 */
    private Integer ranking;
    
    /** 用户名 */
    private String userName;
    
    /** 昵称 */
    private String nickName;
    
    /** 学生ID */
    private Long studentId;
    
    /** 平均分 */
    private Double avgScore;
    
    /** 训练次数 */
    private Integer trainingCount;
    
    /** 徽章数量 */
    private Integer badgeCount;

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(Integer trainingCount) {
        this.trainingCount = trainingCount;
    }

    public Integer getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(Integer badgeCount) {
        this.badgeCount = badgeCount;
    }
}