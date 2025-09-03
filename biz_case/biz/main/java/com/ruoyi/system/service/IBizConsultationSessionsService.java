package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizConsultationSessions;

/**
 * 学生问诊列Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface IBizConsultationSessionsService 
{
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
     * @return 结果
     */
    public int insertBizConsultationSessions(BizConsultationSessions bizConsultationSessions);

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
}
