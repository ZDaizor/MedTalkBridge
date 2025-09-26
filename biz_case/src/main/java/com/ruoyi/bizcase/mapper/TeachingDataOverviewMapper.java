package com.ruoyi.bizcase.mapper;

import com.ruoyi.bizcase.domain.TeachingDataOverview;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教学数据总体概览Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface TeachingDataOverviewMapper {
    
    /**
     * 查询教学数据总体概览
     * 
     * @return 教学数据总体概览
     */
    TeachingDataOverview selectTeachingDataOverview();
}