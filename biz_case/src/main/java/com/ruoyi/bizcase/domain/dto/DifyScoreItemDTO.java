package com.ruoyi.bizcase.domain.dto;

/**
 * Dify评分项DTO
 * 对应Dify返回的单个评分项数据结构
 *
 * @author ruoyi
 * @date 2025-09-21
 */
public class DifyScoreItemDTO {
    
    /** 评分项序号 */
    private String order;
    
    /** 评分项内容描述 */
    private String itemContent;
    
    /** 该项总分 */
    private String score;
    
    /** 实际得分 */
    private String scored;
    
    /** 评分类型 */
    private String type;
    
    /** 评分依据/原因 */
    private String basis;
    
    public DifyScoreItemDTO() {
    }
    
    public DifyScoreItemDTO(String order, String itemContent, String score, String scored, String type, String basis) {
        this.order = order;
        this.itemContent = itemContent;
        this.score = score;
        this.scored = scored;
        this.type = type;
        this.basis = basis;
    }
    
    public String getOrder() {
        return order;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
    
    public String getItemContent() {
        return itemContent;
    }
    
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
    
    public String getScore() {
        return score;
    }
    
    public void setScore(String score) {
        this.score = score;
    }
    
    public String getScored() {
        return scored;
    }
    
    public void setScored(String scored) {
        this.scored = scored;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getBasis() {
        return basis;
    }
    
    public void setBasis(String basis) {
        this.basis = basis;
    }
    
    @Override
    public String toString() {
        return "DifyScoreItemDTO{" +
                "order='" + order + '\'' +
                ", itemContent='" + itemContent + '\'' +
                ", score='" + score + '\'' +
                ", scored='" + scored + '\'' +
                ", type='" + type + '\'' +
                ", basis='" + basis + '\'' +
                '}';
    }
}