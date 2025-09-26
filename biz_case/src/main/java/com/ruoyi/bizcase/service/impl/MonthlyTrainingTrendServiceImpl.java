package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.MonthlyTrainingTrend;
import com.ruoyi.bizcase.mapper.MonthlyTrainingTrendMapper;
import com.ruoyi.bizcase.service.IMonthlyTrainingTrendService;

/**
 * 月度训练趋势Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class MonthlyTrainingTrendServiceImpl implements IMonthlyTrainingTrendService {
    
    @Autowired
    private MonthlyTrainingTrendMapper monthlyTrainingTrendMapper;

    /**
     * 查询月度训练趋势
     * 
     * @return 月度训练趋势列表
     */
    @Override
    public List<MonthlyTrainingTrend> selectMonthlyTrainingTrend() {
        return monthlyTrainingTrendMapper.selectMonthlyTrainingTrend();
    }
}