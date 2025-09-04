package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.BizCase;

/**
 * 病例Service接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface IBizCaseService 
{
    /**
     * 查询病例
     * 
     * @param caseId 病例主键
     * @return 病例
     */
    public BizCase selectBizCaseByCaseId(Long caseId);

    /**
     * 查询病例列表
     * 
     * @param bizCase 病例
     * @return 病例集合
     */
    public List<BizCase> selectBizCaseList(BizCase bizCase);

    /**
     * 新增病例
     * 
     * @param bizCase 病例
     * @return 结果
     */
    public int insertBizCase(BizCase bizCase);

    /**
     * 修改病例
     * 
     * @param bizCase 病例
     * @return 结果
     */
    public int updateBizCase(BizCase bizCase);

    /**
     * 批量删除病例
     * 
     * @param caseIds 需要删除的病例主键集合
     * @return 结果
     */
    public int deleteBizCaseByCaseIds(Long[] caseIds);

    /**
     * 删除病例信息
     * 
     * @param caseId 病例主键
     * @return 结果
     */
    public int deleteBizCaseByCaseId(Long caseId);
}
