package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.DeptStudentStatistics;
import com.ruoyi.bizcase.mapper.DeptStudentStatisticsMapper;
import com.ruoyi.bizcase.service.IDeptStudentStatisticsService;

/**
 * 部门学生统计信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class DeptStudentStatisticsServiceImpl implements IDeptStudentStatisticsService {
    
    @Autowired
    private DeptStudentStatisticsMapper deptStudentStatisticsMapper;

    /**
     * 查询部门学生统计信息
     * 
     * @return 部门学生统计信息
     */
    @Override
    public List<DeptStudentStatistics> selectDeptStudentStatistics() {
        return deptStudentStatisticsMapper.selectDeptStudentStatistics();
    }

    /**
     * 根据部门名称查询部门学生统计信息
     * 
     * @param deptName 部门名称
     * @return 部门学生统计信息
     */
    @Override
    public List<DeptStudentStatistics> selectDeptStudentStatisticsByDeptName(String deptName) {
        return deptStudentStatisticsMapper.selectDeptStudentStatisticsByDeptName(deptName);
    }
}