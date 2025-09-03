package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizCaseMapper;
import com.ruoyi.system.domain.BizCase;
import com.ruoyi.system.service.IBizCaseService;

/**
 * 病例Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class BizCaseServiceImpl implements IBizCaseService 
{
    @Autowired
    private BizCaseMapper bizCaseMapper;

    /**
     * 查询病例
     * 
     * @param caseId 病例主键
     * @return 病例
     */
    @Override
    public BizCase selectBizCaseByCaseId(Long caseId)
    {
        return bizCaseMapper.selectBizCaseByCaseId(caseId);
    }

    /**
     * 查询病例列表
     * 
     * @param bizCase 病例
     * @return 病例
     */
    @Override
    public List<BizCase> selectBizCaseList(BizCase bizCase)
    {
        return bizCaseMapper.selectBizCaseList(bizCase);
    }

    /**
     * 新增病例
     * 
     * @param bizCase 病例
     * @return 结果
     */
    @Override
    public int insertBizCase(BizCase bizCase)
    {
        return bizCaseMapper.insertBizCase(bizCase);
    }

    /**
     * 修改病例
     * 
     * @param bizCase 病例
     * @return 结果
     */
    @Override
    public int updateBizCase(BizCase bizCase)
    {
        return bizCaseMapper.updateBizCase(bizCase);
    }

    /**
     * 批量删除病例
     * 
     * @param caseIds 需要删除的病例主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseByCaseIds(Long[] caseIds)
    {
        return bizCaseMapper.deleteBizCaseByCaseIds(caseIds);
    }

    /**
     * 删除病例信息
     * 
     * @param caseId 病例主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseByCaseId(Long caseId)
    {
        return bizCaseMapper.deleteBizCaseByCaseId(caseId);
    }
}
