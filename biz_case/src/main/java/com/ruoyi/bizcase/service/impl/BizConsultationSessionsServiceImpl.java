package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.mapper.BizConsultationSessionsMapper;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;

/**
 * 学生问诊列Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-04
 */
@Service
public class BizConsultationSessionsServiceImpl implements IBizConsultationSessionsService 
{
    @Autowired
    private BizConsultationSessionsMapper bizConsultationSessionsMapper;

    /**
     * 查询学生问诊列
     * 
     * @param sessionId 学生问诊列主键
     * @return 学生问诊列
     */
    @Override
    public BizConsultationSessions selectBizConsultationSessionsBySessionId(String sessionId)
    {
        return bizConsultationSessionsMapper.selectBizConsultationSessionsBySessionId(sessionId);
    }

    /**
     * 查询学生问诊列列表
     * 
     * @param bizConsultationSessions 学生问诊列
     * @return 学生问诊列
     */
    @Override
    public List<BizConsultationSessions> selectBizConsultationSessionsList(BizConsultationSessions bizConsultationSessions)
    {
        return bizConsultationSessionsMapper.selectBizConsultationSessionsList(bizConsultationSessions);
    }

    /**
     * 新增学生问诊列
     * 
     * @param bizConsultationSessions 学生问诊列
     * @return 结果
     */
    @Override
    public int insertBizConsultationSessions(BizConsultationSessions bizConsultationSessions)
    {
        return bizConsultationSessionsMapper.insertBizConsultationSessions(bizConsultationSessions);
    }

    /**
     * 修改学生问诊列
     * 
     * @param bizConsultationSessions 学生问诊列
     * @return 结果
     */
    @Override
    public int updateBizConsultationSessions(BizConsultationSessions bizConsultationSessions)
    {
        return bizConsultationSessionsMapper.updateBizConsultationSessions(bizConsultationSessions);
    }

    /**
     * 批量删除学生问诊列
     * 
     * @param sessionIds 需要删除的学生问诊列主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSessionsBySessionIds(String[] sessionIds)
    {
        return bizConsultationSessionsMapper.deleteBizConsultationSessionsBySessionIds(sessionIds);
    }

    /**
     * 删除学生问诊列信息
     * 
     * @param sessionId 学生问诊列主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSessionsBySessionId(String sessionId)
    {
        return bizConsultationSessionsMapper.deleteBizConsultationSessionsBySessionId(sessionId);
    }
}
