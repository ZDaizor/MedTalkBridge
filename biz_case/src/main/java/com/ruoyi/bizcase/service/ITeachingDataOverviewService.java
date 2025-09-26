package com.ruoyi.bizcase.service;

import com.ruoyi.bizcase.domain.TeachingDataOverview;

/**
 * 教学数据总体概览Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ITeachingDataOverviewService {
    
    /**
     * 查询教学数据总体概览
     * 
     * @return 教学数据总体概览
     */
    TeachingDataOverview selectTeachingDataOverview();
}