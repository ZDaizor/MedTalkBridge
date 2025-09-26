package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.CaseTypeDistribution;
import org.apache.ibatis.annotations.Mapper;

/**
 * 病例类型分布Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface CaseTypeDistributionMapper {
    
    /**
     * 查询病例类型分布
     * 
     * @return 病例类型分布列表
     */
    List<CaseTypeDistribution> selectCaseTypeDistribution();
}