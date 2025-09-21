package com.ruoyi.bizcase.service;

import java.util.List;

import com.ruoyi.bizcase.domain.BizConsultationSessions;

/**
 * 学生问诊列Service接口
 *
 * @author daizor
 * @date 2025-09-04
 */
public interface IBizConsultationSessionsService {
    /**
     * 查询学生问诊列
     *
     * @param sessionId 学生问诊列主键
     * @return 学生问诊列
     */
    public BizConsultationSessions selectBizConsultationSessionsBySessionId(String sessionId);

    /**
     * 查询学生问诊列列表
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 学生问诊列集合
     */
    public List<BizConsultationSessions> selectBizConsultationSessionsList(BizConsultationSessions bizConsultationSessions);

    /**
     * 新增学生问诊列
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 新增记录的sessionId
     */
    public Long insertBizConsultationSessions(BizConsultationSessions bizConsultationSessions);

    /**
     * 修改学生问诊列
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 结果
     */
    public int updateBizConsultationSessions(BizConsultationSessions bizConsultationSessions);

    /**
     * 批量删除学生问诊列
     *
     * @param sessionIds 需要删除的学生问诊列主键集合
     * @return 结果
     */
    public int deleteBizConsultationSessionsBySessionIds(String[] sessionIds);

    /**
     * 删除学生问诊列信息
     *
     * @param sessionId 学生问诊列主键
     * @return 结果
     */
    public int deleteBizConsultationSessionsBySessionId(String sessionId);

    /**
     * 调用Dify对会话记录进行评分
     *
     * @param sessionId 会话ID
     * @param caseId 病例ID
     * @param stepId 步骤ID
     * @return 评分结果的JSON字符串
     */
    public String evaluateSessionWithDify(String sessionId, Long caseId, Long stepId);

    /**
     * 解析Dify评分结果并保存到评分详情表
     *
     * @param difyResult Dify返回的评分JSON结果
     * @param evaluationId 主评价表ID
     * @return 保存成功的记录数
     */
    public int saveDifyScoreDetails(String difyResult, Long evaluationId);
}
