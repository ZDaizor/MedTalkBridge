package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.LearningEffectivenessTrend;

/**
 * 学习效果趋势Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-27
 */
public interface LearningEffectivenessTrendMapper 
{
    /**
     * 查询学习效果趋势
     * 
     * @return 学习效果趋势集合
     */
    public List<LearningEffectivenessTrend> selectLearningEffectivenessTrend();
}