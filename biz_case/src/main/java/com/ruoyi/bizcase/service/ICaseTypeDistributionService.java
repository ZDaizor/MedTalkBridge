package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.CaseTypeDistribution;

/**
 * 病例类型分布Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ICaseTypeDistributionService {
    
    /**
     * 查询病例类型分布
     * 
     * @return 病例类型分布列表
     */
    List<CaseTypeDistribution> selectCaseTypeDistribution();
}