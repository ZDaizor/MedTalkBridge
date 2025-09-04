package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizCaseStep;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface BizCaseStepMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param stepId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBizCaseStepByStepId(Long stepId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param stepIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizCaseStepByStepIds(Long[] stepIds);
}
