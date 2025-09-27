package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.LearningEffectivenessTrend;
import com.ruoyi.bizcase.mapper.LearningEffectivenessTrendMapper;
import com.ruoyi.bizcase.service.ILearningEffectivenessTrendService;

/**
 * 学习效果趋势Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-27
 */
@Service
public class LearningEffectivenessTrendServiceImpl implements ILearningEffectivenessTrendService
{
    @Autowired
    private LearningEffectivenessTrendMapper learningEffectivenessTrendMapper;

    /**
     * 查询学习效果趋势
     * 
     * @return 学习效果趋势集合
     */
    @Override
    public List<LearningEffectivenessTrend> selectLearningEffectivenessTrend()
    {
        return learningEffectivenessTrendMapper.selectLearningEffectivenessTrend();
    }
}