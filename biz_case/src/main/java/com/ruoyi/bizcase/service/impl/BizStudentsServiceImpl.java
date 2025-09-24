package com.ruoyi.bizcase.service.impl;

import com.ruoyi.bizcase.mapper.BizStudentsMapper;
import com.ruoyi.bizcase.service.IBizStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BizStudentsServiceImpl implements IBizStudentsService {
    @Autowired
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
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("evalMode", 1);

        return bizStudentsMapper.sumTrainingMinutesByUserId(params);
    }

    /**
     * 查询学生的平均得分
     *
     * @param userId 用户主键
     * @return 平均得分
     */
    @Override
    public Double getAverageScoreByUserId(Long userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("evalMode", 1);

        return bizStudentsMapper.getAverageScoreByUserId(params);
    }

    @Override
    public List<Map<String, Object>> getTrainingList(Long userId,String caseName) {
        return bizStudentsMapper.getTrainingList(userId,caseName,1);
    }

    @Override
    public List<Map<String, Object>> getExamList(Long userId,String caseName) {
        return bizStudentsMapper.getTrainingList(userId,caseName,2);
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
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("evalMode", 2);
        return bizStudentsMapper.sumTrainingMinutesByUserId(params);
    }

    @Override
    public Double getAverageScoreExamByUserId(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("evalMode", 2);
        return bizStudentsMapper.getAverageScoreByUserId(params);
    }

    @Override
    public int countHighScoreExamSessionsByUserId(Long userId) {
        return bizStudentsMapper.countHighScoreExamSessionsByUserId(userId,2);
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getActivityList(Long userId) {
        return bizStudentsMapper.getActivityList(userId);
    }

    @Override
    public Double getTrainingAverageScoreByUserId(Long userId,Integer evalMode) {
        return bizStudentsMapper.getTrainingAverageScoreByUserId(userId,evalMode);
    }

    @Override
    public int countUserBadges(Long userId) {
        return bizStudentsMapper.countUserBadges(userId);
    }
}
