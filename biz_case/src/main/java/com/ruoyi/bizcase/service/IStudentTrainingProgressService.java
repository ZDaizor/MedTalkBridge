package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.StudentTrainingProgress;

/**
 * 学生训练进度统计服务层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IStudentTrainingProgressService {
    /**
     * 查询学生训练进度统计
     * 
     * @return 学生训练进度统计集合
     */
    List<StudentTrainingProgress> selectStudentTrainingProgress();
}