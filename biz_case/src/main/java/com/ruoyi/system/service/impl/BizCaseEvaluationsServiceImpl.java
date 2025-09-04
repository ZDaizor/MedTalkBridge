package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizCaseEvaluationsMapper;
import com.ruoyi.system.domain.BizCaseEvaluations;
import com.ruoyi.system.service.IBizCaseEvaluationsService;

/**
 * 学生病例得分总Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-04
 */
@Service
public class BizCaseEvaluationsServiceImpl implements IBizCaseEvaluationsService 
{
    @Autowired
    private BizCaseEvaluationsMapper bizCaseEvaluationsMapper;

    /**
     * 查询学生病例得分总
     * 
     * @param evaluationId 学生病例得分总主键
     * @return 学生病例得分总
     */
    @Override
    public BizCaseEvaluations selectBizCaseEvaluationsByEvaluationId(Long evaluationId)
    {
        return bizCaseEvaluationsMapper.selectBizCaseEvaluationsByEvaluationId(evaluationId);
    }

    /**
     * 查询学生病例得分总列表
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 学生病例得分总
     */
    @Override
    public List<BizCaseEvaluations> selectBizCaseEvaluationsList(BizCaseEvaluations bizCaseEvaluations)
    {
        return bizCaseEvaluationsMapper.selectBizCaseEvaluationsList(bizCaseEvaluations);
    }

    /**
     * 新增学生病例得分总
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 结果
     */
    @Override
    public int insertBizCaseEvaluations(BizCaseEvaluations bizCaseEvaluations)
    {
        return bizCaseEvaluationsMapper.insertBizCaseEvaluations(bizCaseEvaluations);
    }

    /**
     * 修改学生病例得分总
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 结果
     */
    @Override
    public int updateBizCaseEvaluations(BizCaseEvaluations bizCaseEvaluations)
    {
        return bizCaseEvaluationsMapper.updateBizCaseEvaluations(bizCaseEvaluations);
    }

    /**
     * 批量删除学生病例得分总
     * 
     * @param evaluationIds 需要删除的学生病例得分总主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseEvaluationsByEvaluationIds(Long[] evaluationIds)
    {
        return bizCaseEvaluationsMapper.deleteBizCaseEvaluationsByEvaluationIds(evaluationIds);
    }

    /**
     * 删除学生病例得分总信息
     * 
     * @param evaluationId 学生病例得分总主键
     * @return 结果
     */
    @Override
    public int deleteBizCaseEvaluationsByEvaluationId(Long evaluationId)
    {
        return bizCaseEvaluationsMapper.deleteBizCaseEvaluationsByEvaluationId(evaluationId);
    }
}
