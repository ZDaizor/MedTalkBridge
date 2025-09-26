package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.MonthlyTrainingTrend;

/**
 * 月度训练趋势Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IMonthlyTrainingTrendService {
    
    /**
     * 查询月度训练趋势
     * 
     * @return 月度训练趋势列表
     */
    List<MonthlyTrainingTrend> selectMonthlyTrainingTrend();
}