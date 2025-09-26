package com.ruoyi.bizcase.repository.impl;

import com.ruoyi.bizcase.domain.DashboardStatistics;
import com.ruoyi.bizcase.domain.dto.StudentStatisticsQueryDTO;
import com.ruoyi.bizcase.repository.DashboardStatisticsRepository;
import com.ruoyi.bizcase.mapper.DashboardStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 仪表板统计数据访问实现类
 *
 * @author ruoyi
 * @date 2025-09-24
 */
@Repository
public class DashboardStatisticsRepositoryImpl implements DashboardStatisticsRepository {
    @Autowired
    private DashboardStatisticsMapper dashboardStatisticsMapper;

    @Override
    public DashboardStatistics getDashboardStatistics() {
        Map<String, Object> statistics = getRawDashboardStatistics();

        Long totalStudents = statistics.get("totalStudents") != null ?
                Long.valueOf(statistics.get("totalStudents").toString()) : 0L;
        Double averageScore = statistics.get("averageScore") != null ?
                Double.valueOf(statistics.get("averageScore").toString()) : 0.0;
        Long totalLearningDuration = statistics.get("totalLearningDuration") != null ?
                Long.valueOf(statistics.get("totalLearningDuration").toString()) : 0L;
        Long totalExamCount = statistics.get("totalExamCount") != null ?
                Long.valueOf(statistics.get("totalExamCount").toString()) : 0L;

        return new DashboardStatistics(totalStudents, averageScore, totalLearningDuration, totalExamCount);
    }

    @Override
    public Map<String, Object> getRawDashboardStatistics() {
        return dashboardStatisticsMapper.selectDashboardStatistics();
    }

    @Override
    public DashboardStatistics getDashboardStatisticsByParams(StudentStatisticsQueryDTO queryDTO) {
        Map<String, Object> params = new HashMap<>();
        params.put("deptId", queryDTO.getDeptId());
        params.put("studentName", queryDTO.getStudentName());

        Map<String, Object> statistics = dashboardStatisticsMapper.selectDashboardStatisticsByParams(params);

        Long totalStudents = statistics.get("totalStudents") != null ?
                Long.valueOf(statistics.get("totalStudents").toString()) : 0L;
        Double averageScore = statistics.get("averageScore") != null ?
                Double.valueOf(statistics.get("averageScore").toString()) : 0.0;
        Long totalLearningDuration = statistics.get("totalLearningDuration") != null ?
                Long.valueOf(statistics.get("totalLearningDuration").toString()) : 0L;
        Long totalExamCount = statistics.get("totalExamCount") != null ?
                Long.valueOf(statistics.get("totalExamCount").toString()) : 0L;

        return new DashboardStatistics(totalStudents, averageScore, totalLearningDuration, totalExamCount);
    }
}