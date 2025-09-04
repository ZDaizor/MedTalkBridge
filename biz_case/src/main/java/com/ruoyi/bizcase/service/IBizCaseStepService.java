package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.BizCaseStep;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface IBizCaseStepService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param stepId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BizCaseStep selectBizCaseStepByStepId(Long stepId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BizCaseStep> selectBizCaseStepList(BizCaseStep bizCaseStep);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 结果
     */
    public int insertBizCaseStep(BizCaseStep bizCaseStep);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 结果
     */
    public int updateBizCaseStep(BizCaseStep bizCaseStep);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param stepIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBizCaseStepByStepIds(Long[] stepIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param stepId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBizCaseStepByStepId(Long stepId);
}
