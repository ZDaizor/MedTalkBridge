package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.BizCasePrompt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
@Mapper
public interface BizCasePromptMapper
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
     * 删除【请填写功能名称】
     * 
     * @param promptId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBizCasePromptByPromptId(Long promptId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param promptIds 需要删除的数据主键集���
     * @return 结果
     */
    public int deleteBizCasePromptByPromptIds(Long[] promptIds);

    /**
     * 根据caseId和stepId查询promptKey
     */
    public String selectPromptKeyByCaseIdAndStepId(@Param("caseId") Long caseId, @Param("stepId") Long stepId);
}
