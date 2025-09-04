package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizConsultationSeMessagesMapper;
import com.ruoyi.system.domain.BizConsultationSeMessages;
import com.ruoyi.system.service.IBizConsultationSeMessagesService;

/**
 * 问诊对话记录详情Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-04
 */
@Service
public class BizConsultationSeMessagesServiceImpl implements IBizConsultationSeMessagesService 
{
    @Autowired
    private BizConsultationSeMessagesMapper bizConsultationSeMessagesMapper;

    /**
     * 查询问诊对话记录详情
     * 
     * @param messagesId 问诊对话记录详情主键
     * @return 问诊对话记录详情
     */
    @Override
    public BizConsultationSeMessages selectBizConsultationSeMessagesByMessagesId(String messagesId)
    {
        return bizConsultationSeMessagesMapper.selectBizConsultationSeMessagesByMessagesId(messagesId);
    }

    /**
     * 查询问诊对话记录详情列表
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 问诊对话记录详情
     */
    @Override
    public List<BizConsultationSeMessages> selectBizConsultationSeMessagesList(BizConsultationSeMessages bizConsultationSeMessages)
    {
        return bizConsultationSeMessagesMapper.selectBizConsultationSeMessagesList(bizConsultationSeMessages);
    }

    /**
     * 新增问诊对话记录详情
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 结果
     */
    @Override
    public int insertBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages)
    {
        return bizConsultationSeMessagesMapper.insertBizConsultationSeMessages(bizConsultationSeMessages);
    }

    /**
     * 修改问诊对话记录详情
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 结果
     */
    @Override
    public int updateBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages)
    {
        return bizConsultationSeMessagesMapper.updateBizConsultationSeMessages(bizConsultationSeMessages);
    }

    /**
     * 批量删除问诊对话记录详情
     * 
     * @param messagesIds 需要删除的问诊对话记录详情主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSeMessagesByMessagesIds(String[] messagesIds)
    {
        return bizConsultationSeMessagesMapper.deleteBizConsultationSeMessagesByMessagesIds(messagesIds);
    }

    /**
     * 删除问诊对话记录详情信息
     * 
     * @param messagesId 问诊对话记录详情主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSeMessagesByMessagesId(String messagesId)
    {
        return bizConsultationSeMessagesMapper.deleteBizConsultationSeMessagesByMessagesId(messagesId);
    }
}
