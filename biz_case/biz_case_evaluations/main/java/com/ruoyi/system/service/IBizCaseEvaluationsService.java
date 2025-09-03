package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizCaseEvaluations;

/**
 * 学生病例得分总Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface IBizCaseEvaluationsService 
{
    /**
     * 查询学生病例得分总
     * 
     * @param evaluationId 学生病例得分总主键
     * @return 学生病例得分总
     */
    public BizCaseEvaluations selectBizCaseEvaluationsByEvaluationId(Long evaluationId);

    /**
     * 查询学生病例得分总列表
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 学生病例得分总集合
     */
    public List<BizCaseEvaluations> selectBizCaseEvaluationsList(BizCaseEvaluations bizCaseEvaluations);

    /**
     * 新增学生病例得分总
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 结果
     */
    public int insertBizCaseEvaluations(BizCaseEvaluations bizCaseEvaluations);

    /**
     * 修改学生病例得分总
     * 
     * @param bizCaseEvaluations 学生病例得分总
     * @return 结果
     */
    public int updateBizCaseEvaluations(BizCaseEvaluations bizCaseEvaluations);

    /**
     * 批量删除学生病例得分总
     * 
     * @param evaluationIds 需要删除的学生病例得分总主键集合
     * @return 结果
     */
    public int deleteBizCaseEvaluationsByEvaluationIds(Long[] evaluationIds);

    /**
     * 删除学生病例得分总信息
     * 
     * @param evaluationId 学生病例得分总主键
     * @return 结果
     */
    public int deleteBizCaseEvaluationsByEvaluationId(Long evaluationId);
}
