package com.ruoyi.bizcase.domain.dto;

/**
 * 学生统计查询参数对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public class StudentStatisticsQueryDTO
{
    /** 部门ID（学校） */
    private Long deptId;

    /** 学生姓名 */
    private String studentName;

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }
}