package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.StudentTrainingProgress;
import com.ruoyi.bizcase.mapper.StudentTrainingProgressMapper;
import com.ruoyi.bizcase.service.IStudentTrainingProgressService;

/**
 * 学生训练进度统计服务层实现
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class StudentTrainingProgressServiceImpl implements IStudentTrainingProgressService {
    
    @Autowired
    private StudentTrainingProgressMapper studentTrainingProgressMapper;

    /**
     * 查询学生训练进度统计
     * 
     * @return 学生训练进度统计集合
     */
    @Override
    public List<StudentTrainingProgress> selectStudentTrainingProgress() {
        return studentTrainingProgressMapper.selectStudentTrainingProgress();
    }
}