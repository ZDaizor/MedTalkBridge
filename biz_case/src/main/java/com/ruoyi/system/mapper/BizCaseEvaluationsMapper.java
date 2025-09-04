package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizCaseEvaluations;

/**
 * 学生病例得分总Mapper接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface BizCaseEvaluationsMapper 
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
     * 删除学生病例得分总
     * 
     * @param evaluationId 学生病例得分总主键
     * @return 结果
     */
    public int deleteBizCaseEvaluationsByEvaluationId(Long evaluationId);

    /**
     * 批量删除学生病例得分总
     * 
     * @param evaluationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizCaseEvaluationsByEvaluationIds(Long[] evaluationIds);
}
