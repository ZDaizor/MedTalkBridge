package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.MonthlyTrainingTrend;
import org.apache.ibatis.annotations.Mapper;

/**
 * 月度训练趋势Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface MonthlyTrainingTrendMapper {
    
    /**
     * 查询月度训练趋势
     * 
     * @return 月度训练趋势列表
     */
    List<MonthlyTrainingTrend> selectMonthlyTrainingTrend();
}