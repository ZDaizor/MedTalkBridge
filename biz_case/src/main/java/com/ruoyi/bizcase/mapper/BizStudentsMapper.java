package com.ruoyi.bizcase.mapper;

import com.ruoyi.bizcase.domain.dto.TrainExamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    double getTrainingAverageScoreByUserId(@Param("userId") Long userId,@Param("evalMode") Integer evalMode);



    List<TrainExamInfo> getTrainingList(@Param("userId") Long userId, @Param("caseName") String caseName, @Param("evalMode")  int evalMode);

    int countExamSessionsByUserId(@Param("userId") Long userId,@Param("evalMode")  int evalMode);

    /**
     * 查询学生考试A（高分，>85分）次数
     * @param userId 学生ID
     * @return A级考试次数
     */
    int countHighScoreExamSessionsByUserId(@Param("userId") Long userId,@Param("evalMode")  int evalMode);

    /**
     * 查询学生活跃列表
     * @param userId 学生ID
     * @return 活跃列表
     */
    java.util.List<java.util.Map<String, Object>> getActivityList(Long userId);

    /**
     * 查询用户获得的徽章数量
     * @param userId 用户ID
     * @return 徽章数量
     */
    int countUserBadges(Long userId);

    /**
     * 查询某个步骤的准确率
     * @param userId 用户ID
     * @param stepId 步骤ID
     * @return 准确率（平均分）
     */
    Double getStepAccuracyRate(@Param("userId") Long userId,@Param("stepId")   Integer stepId);

    /**
     * 统计用户完成的病历数量（所有模式）
     * @param userId 用户ID
     * @return 完成病历数量
     */
    int countFinishedCases(Long userId);

    /**
     * 查询用户近5个月训练数量统计
     * @param userId 用户ID
     * @return 每月训练数量列表
     */
    java.util.List<java.util.Map<String, Object>> getRecentMonthTrainingStats(Long userId);
}
