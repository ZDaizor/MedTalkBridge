package com.ruoyi.bizcase.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.bizcase.domain.DeptStudentStatistics;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门学生统计信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface DeptStudentStatisticsMapper {
    
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