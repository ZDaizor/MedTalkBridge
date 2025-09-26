package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.CaseTypeDistribution;
import com.ruoyi.bizcase.mapper.CaseTypeDistributionMapper;
import com.ruoyi.bizcase.service.ICaseTypeDistributionService;

/**
 * 病例类型分布Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class CaseTypeDistributionServiceImpl implements ICaseTypeDistributionService {
    
    @Autowired
    private CaseTypeDistributionMapper caseTypeDistributionMapper;

    /**
     * 查询病例类型分布
     * 
     * @return 病例类型分布列表
     */
    @Override
    public List<CaseTypeDistribution> selectCaseTypeDistribution() {
        return caseTypeDistributionMapper.selectCaseTypeDistribution();
    }
}