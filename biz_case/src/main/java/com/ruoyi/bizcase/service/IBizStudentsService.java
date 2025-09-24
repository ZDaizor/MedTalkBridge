package com.ruoyi.bizcase.service;
import java.util.List;
import java.util.Map;

public interface IBizStudentsService {

    /**
     * 查询学生已完成全部训练病历的总数
     * @param userId 学生ID
     * @return 已完成病历总数
     */
    public int countCompletedCasesByUserId(Long userId);

    /**
     * 查询指定用户训练模式下的总学习时长（分钟）
     * @param userId 用户ID
     * @return 总时长（分钟）
     */
    public int sumTrainingMinutesByUserId(Long userId);

    /**
     * 查询学生训练模式下已完成病例的平均得分
     * @param userId 学生ID
     * @return 平均得分（如无成绩返回null）
     */
    public Double getAverageScoreByUserId(Long userId);

    /**
     * 查询学生训练明细列表
     * @param userId 学生ID
     * @return 训练明细列表
     */
    List<Map<String, Object>> getTrainingList(Long userId,String caseName);

    List<Map<String, Object>> getExamList(Long userId,String caseName);

    /**
     * 查询学生考试次数
     * @param userId 学生ID
     * @return 考试次数
     */
    int countExamSessionsByUserId(Long userId);

    /**
     * 获取考试总时长
     * @param userId
     * @return
     */
    int sumExamMinutesByUserId(Long userId);


    Double getAverageScoreExamByUserId(Long userId);

    /**
     * 查询学生考试A（高分，>85分）次数
     * @param userId 学生ID
     * @return A级考试次数
     */
    int countHighScoreExamSessionsByUserId(Long userId);

    /**
     * 查询学生活跃列表
     * @param userId 学生ID
     * @return 活跃列表
     */
    List<Map<String, Object>> getActivityList(Long userId);

    Double getTrainingAverageScoreByUserId(Long userId,Integer evalMode);

    /**
     * 查询用户获得的徽章数量
     * @param userId 用户ID
     * @return 徽章数量
     */
    int countUserBadges(Long userId);
}
