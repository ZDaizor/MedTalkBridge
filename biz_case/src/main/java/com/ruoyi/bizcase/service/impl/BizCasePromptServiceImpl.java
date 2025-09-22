package com.ruoyi.bizcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.mapper.BizCasePromptMapper;
import com.ruoyi.bizcase.domain.BizCasePrompt;
import com.ruoyi.bizcase.service.IBizCasePromptService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author daizor
 * @date 2025-09-04
 */
@Service
public class BizCasePromptServiceImpl implements IBizCasePromptService {
    @Autowired
    private BizCasePromptMapper bizCasePromptMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param promptId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BizCasePrompt selectBizCasePromptByPromptId(Long promptId) {
        return bizCasePromptMapper.selectBizCasePromptByPromptId(promptId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param bizCasePrompt 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BizCasePrompt> selectBizCasePromptList(BizCasePrompt bizCasePrompt) {
        return bizCasePromptMapper.selectBizCasePromptList(bizCasePrompt);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param bizCasePrompt 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBizCasePrompt(BizCasePrompt bizCasePrompt) {
        return bizCasePromptMapper.insertBizCasePrompt(bizCasePrompt);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param bizCasePrompt 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBizCasePrompt(BizCasePrompt bizCasePrompt) {
        return bizCasePromptMapper.updateBizCasePrompt(bizCasePrompt);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param promptIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBizCasePromptByPromptIds(Long[] promptIds) {
        return bizCasePromptMapper.deleteBizCasePromptByPromptIds(promptIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param promptId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBizCasePromptByPromptId(Long promptId) {
        return bizCasePromptMapper.deleteBizCasePromptByPromptId(promptId);
    }


    @Override
    public String getPromptKeyByCaseIdAndStepId(Long caseId, Long stepId) {
        return bizCasePromptMapper.selectPromptKeyByCaseIdAndStepId(caseId, stepId);
    }

    @Override
    public String getPromptKeyScoreByCaseIdAndStepId(Long caseId, Long stepId) {
        return bizCasePromptMapper.selectPromptKeyScoreByCaseIdAndStepId(caseId, stepId);
    }
}
