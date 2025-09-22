package com.ruoyi.bizcase.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface BizStudentsMapper {


    /**
     * 查询学生已完成全部训练病历的总数
     *
     * @param userId 学生ID
     * @return 已完成病历总数
     */
    int countCompletedCasesByUserId(Long userId);

    /**
     * 查询指定用户训练模式下的总学习时长（分钟）
     * @param params
     * @return 总时长（分钟）
     */
    int sumTrainingMinutesByUserId(Map<String,Object> params);


    /**
     * 获取平均成绩
     * @param params
     * @return
     */
    double getAverageScoreByUserId(Map<String,Object> params);



    List<Map<String, Object>> getTrainingList(Long userId,String caseName,int evalMode);

    int countExamSessionsByUserId(Long userId,int evalMode);

    /**
     * 查询学生考试A（高分，>85分）次数
     * @param userId 学生ID
     * @return A级考试次数
     */
    int countHighScoreExamSessionsByUserId(Long userId,int evalMode);

    /**
     * 查询学生活跃列表
     * @param userId 学生ID
     * @return 活跃列表
     */
    java.util.List<java.util.Map<String, Object>> getActivityList(Long userId);
}
