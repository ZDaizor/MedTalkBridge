package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizCaseStepMapper;
import com.ruoyi.system.domain.BizCaseStep;
import com.ruoyi.system.service.IBizCaseStepService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class BizCaseStepServiceImpl implements IBizCaseStepService 
{
    @Autowired
    private BizCaseStepMapper bizCaseStepMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param stepId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BizCaseStep selectBizCaseStepByStepId(Long stepId)
    {
        return bizCaseStepMapper.selectBizCaseStepByStepId(stepId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BizCaseStep> selectBizCaseStepList(BizCaseStep bizCaseStep)
    {
        return bizCaseStepMapper.selectBizCaseStepList(bizCaseStep);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBizCaseStep(BizCaseStep bizCaseStep)
    {
        return bizCaseStepMapper.insertBizCaseStep(bizCaseStep);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param bizCaseStep 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBizCaseStep(BizCaseStep bizCaseStep)
    {
        return bizCaseStepMapper.updateBizCaseStep(bizCaseStep);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param stepIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseStepByStepIds(Long[] stepIds)
    {
        return bizCaseStepMapper.deleteBizCaseStepByStepIds(stepIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param stepId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseStepByStepId(Long stepId)
    {
        return bizCaseStepMapper.deleteBizCaseStepByStepId(stepId);
    }
}
