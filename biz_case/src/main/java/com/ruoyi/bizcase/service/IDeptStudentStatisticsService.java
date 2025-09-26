package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.DeptStudentStatistics;

/**
 * 部门学生统计信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IDeptStudentStatisticsService {
    
    /**
     * 查询部门学生统计信息
     * 
     * @return 部门学生统计信息列表
     */
    List<DeptStudentStatistics> selectDeptStudentStatistics();
    
    /**
     * 根据部门名称查询部门学生统计信息
     * 
     * @param deptName 部门名称
     * @return 部门学生统计信息列表
     */
    List<DeptStudentStatistics> selectDeptStudentStatisticsByDeptName(String deptName);
}