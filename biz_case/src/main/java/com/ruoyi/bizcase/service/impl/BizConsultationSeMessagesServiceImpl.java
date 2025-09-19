package com.ruoyi.bizcase.service.impl;

import com.ruoyi.bizcase.domain.BizConsultationSeMessages;
import com.ruoyi.bizcase.mapper.BizConsultationSeMessagesMapper;
import com.ruoyi.bizcase.service.IBizConsultationSeMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问诊对话记录详情 Service 实现
 */
@Service
public class BizConsultationSeMessagesServiceImpl implements IBizConsultationSeMessagesService {

    @Autowired
    private BizConsultationSeMessagesMapper bizConsultationSeMessagesMapper;

    @Override
    public BizConsultationSeMessages selectBizConsultationSeMessagesByMessagesId(String messagesId) {
        return bizConsultationSeMessagesMapper.selectBizConsultationSeMessagesByMessagesId(messagesId);
    }

    @Override
    public List<BizConsultationSeMessages> selectBizConsultationSeMessagesList(BizConsultationSeMessages bizConsultationSeMessages) {
        return bizConsultationSeMessagesMapper.selectBizConsultationSeMessagesList(bizConsultationSeMessages);
    }

    @Override
    public int insertBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages) {
        return bizConsultationSeMessagesMapper.insertBizConsultationSeMessages(bizConsultationSeMessages);
    }

    @Override
    public int updateBizConsultationSeMessages(BizConsultationSeMessages bizConsultationSeMessages) {
        return bizConsultationSeMessagesMapper.updateBizConsultationSeMessages(bizConsultationSeMessages);
    }

    @Override
    public int deleteBizConsultationSeMessagesByMessagesIds(String[] messagesIds) {
        return bizConsultationSeMessagesMapper.deleteBizConsultationSeMessagesByMessagesIds(messagesIds);
    }

    @Override
    public int deleteBizConsultationSeMessagesByMessagesId(String messagesId) {
        return bizConsultationSeMessagesMapper.deleteBizConsultationSeMessagesByMessagesId(messagesId);
    }
}
