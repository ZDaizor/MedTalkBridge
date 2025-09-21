package com.ruoyi.bizcase.service.impl;

import com.ruoyi.bizcase.mapper.BizStudentsMapper;
import com.ruoyi.bizcase.service.IBizStudentsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BizStudentsServiceImpl implements IBizStudentsService {

    private BizStudentsMapper bizStudentsMapper;


    /**
     * 统计用户已完成的病例数
     *
     * @param userId 用户主键
     * @return 已完成的病例数
     */
    @Override
    public int countCompletedCasesByUserId(Long userId) {
        return bizStudentsMapper.countCompletedCasesByUserId(userId);
    }

    /**
     * 统计用户的训练时长
     *
     * @param userId 用户主键
     * @return 训练时长
     */
    @Override
    public int sumTrainingMinutesByUserId(Long userId) {
        return bizStudentsMapper.sumTrainingMinutesByUserId(userId,1);
    }

    /**
     * 查询学生的平均得分
     *
     * @param userId 用户主键
     * @return 平均得分
     */
    @Override
    public Double getAverageScoreByUserId(Long userId) {
        return bizStudentsMapper.getAverageScoreByUserId(userId,1);
    }

    @Override
    public List<Map<String, Object>> getTrainingList(Long userId) {
        return bizStudentsMapper.getTrainingList(userId,1);
    }

    @Override
    public List<Map<String, Object>> getExamList(Long userId) {
        return bizStudentsMapper.getTrainingList(userId,2);
    }

    /**
     * 获取考试次数
     * @param userId 学生ID
     * @return
     */
    @Override
    public int countExamSessionsByUserId(Long userId) {
        return bizStudentsMapper.countExamSessionsByUserId(userId,2);
    }

    @Override
    public int sumExamMinutesByUserId(Long userId) {
        return bizStudentsMapper.sumTrainingMinutesByUserId(userId,2);
    }

    @Override
    public Double getAverageScoreExamByUserId(Long userId) {
        return bizStudentsMapper.getAverageScoreByUserId(userId,2);
    }

    @Override
    public int countHighScoreExamSessionsByUserId(Long userId) {
        return bizStudentsMapper.countHighScoreExamSessionsByUserId(userId,2);
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getActivityList(Long userId) {
        return bizStudentsMapper.getActivityList(userId);
    }
}
