package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizCasePrompt;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface IBizCasePromptService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param promptId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BizCasePrompt selectBizCasePromptByPromptId(Long promptId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bizCasePrompt 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BizCasePrompt> selectBizCasePromptList(BizCasePrompt bizCasePrompt);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bizCasePrompt 【请填写功能名称】
     * @return 结果
     */
    public int insertBizCasePrompt(BizCasePrompt bizCasePrompt);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bizCasePrompt 【请填写功能名称】
     * @return 结果
     */
    public int updateBizCasePrompt(BizCasePrompt bizCasePrompt);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param promptIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBizCasePromptByPromptIds(Long[] promptIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param promptId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBizCasePromptByPromptId(Long promptId);
}
