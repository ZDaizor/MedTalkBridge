package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizScoreTemplates;

/**
 * 内容得分模板Service接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface IBizScoreTemplatesService 
{
    /**
     * 查询内容得分模板
     * 
     * @param templatesId 内容得分模板主键
     * @return 内容得分模板
     */
    public BizScoreTemplates selectBizScoreTemplatesByTemplatesId(Long templatesId);

    /**
     * 查询内容得分模板列表
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 内容得分模板集合
     */
    public List<BizScoreTemplates> selectBizScoreTemplatesList(BizScoreTemplates bizScoreTemplates);

    /**
     * 新增内容得分模板
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 结果
     */
    public int insertBizScoreTemplates(BizScoreTemplates bizScoreTemplates);

    /**
     * 修改内容得分模板
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 结果
     */
    public int updateBizScoreTemplates(BizScoreTemplates bizScoreTemplates);

    /**
     * 批量删除内容得分模板
     * 
     * @param templatesIds 需要删除的内容得分模板主键集合
     * @return 结果
     */
    public int deleteBizScoreTemplatesByTemplatesIds(Long[] templatesIds);

    /**
     * 删除内容得分模板信息
     * 
     * @param templatesId 内容得分模板主键
     * @return 结果
     */
    public int deleteBizScoreTemplatesByTemplatesId(Long templatesId);
}
