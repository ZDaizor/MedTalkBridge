package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizConsultationSeMessages;

/**
 * 问诊对话记录详情Mapper接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface BizConsultationSeMessagesMapper 
{
    /**
     * 查询问诊对话记录详情
     * 
     * @param messagesId 问诊对话记录详情主键
     * @return 问诊对话记录详情
     */
    public BizConsultationSeMessages selectBizConsultationSeMessagesByMessagesId(String messagesId);

    /**
     * 查询问诊对话记录详情列表
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 问诊对话记录详情集合
     */
    public List<BizConsultationSeMessages> selectBizConsultationSeMessagesList(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 新增问诊对话记录详情
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 结果
     */
    public int insertBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 修改问诊对话记录详情
     * 
     * @param bizConsultationSeMessages 问诊对话记录详情
     * @return 结果
     */
    public int updateBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 删除问诊对话记录详情
     * 
     * @param messagesId 问诊对话记录详情主键
     * @return 结果
     */
    public int deleteBizConsultationSeMessagesByMessagesId(String messagesId);

    /**
     * 批量删除问诊对话记录详情
     * 
     * @param messagesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizConsultationSeMessagesByMessagesIds(String[] messagesIds);
}
