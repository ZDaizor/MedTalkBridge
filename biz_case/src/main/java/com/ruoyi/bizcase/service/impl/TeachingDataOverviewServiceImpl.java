package com.ruoyi.bizcase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.TeachingDataOverview;
import com.ruoyi.bizcase.mapper.TeachingDataOverviewMapper;
import com.ruoyi.bizcase.service.ITeachingDataOverviewService;

/**
 * 教学数据总体概览Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class TeachingDataOverviewServiceImpl implements ITeachingDataOverviewService {
    
    @Autowired
    private TeachingDataOverviewMapper teachingDataOverviewMapper;

    /**
     * 查询教学数据总体概览
     * 
     * @return 教学数据总体概览
     */
    @Override
    public TeachingDataOverview selectTeachingDataOverview() {
        return teachingDataOverviewMapper.selectTeachingDataOverview();
    }
}