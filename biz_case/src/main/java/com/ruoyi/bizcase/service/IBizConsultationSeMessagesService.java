package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.BizConsultationSeMessages;

/**
 * 问诊对话记录详情Service接口
 *
 * 按照若依代码生成规范提供常规 CRUD 方法。
 */
public interface IBizConsultationSeMessagesService {
    /**
     * 查询单条记录
     * @param messagesId 主键
     * @return 记录
     */
    BizConsultationSeMessages selectBizConsultationSeMessagesByMessagesId(String messagesId);

    /**
     * 查询列表
     * @param bizConsultationSeMessages 过滤条件
     * @return 列表
     */
    List<BizConsultationSeMessages> selectBizConsultationSeMessagesList(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 新增
     * @param bizConsultationSeMessages 实体
     * @return 影响行数
     */
    int insertBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 修改
     * @param bizConsultationSeMessages 实体
     * @return 影响行数
     */
    int updateBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages);

    /**
     * 批量删除
     * @param messagesIds 主键数组
     * @return 影响行数
     */
    int deleteBizConsultationSeMessagesByMessagesIds(String[] messagesIds);

    /**
     * 删除单条
     * @param messagesId 主键
     * @return 影响行数
     */
    int deleteBizConsultationSeMessagesByMessagesId(String messagesId);
}
