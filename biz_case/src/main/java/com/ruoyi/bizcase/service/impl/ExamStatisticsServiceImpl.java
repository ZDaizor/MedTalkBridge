package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.ExamStatistics;
import com.ruoyi.bizcase.mapper.ExamStatisticsMapper;
import com.ruoyi.bizcase.service.IExamStatisticsService;

/**
 * 考试统计服务层实现
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class ExamStatisticsServiceImpl implements IExamStatisticsService {
    
    @Autowired
    private ExamStatisticsMapper examStatisticsMapper;

    /**
     * 查询考试统计
     * 
     * @return 考试统计集合
     */
    @Override
    public List<ExamStatistics> selectExamStatistics() {
        return examStatisticsMapper.selectExamStatistics();
    }
}