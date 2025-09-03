package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizScoreTemplatesMapper;
import com.ruoyi.system.domain.BizScoreTemplates;
import com.ruoyi.system.service.IBizScoreTemplatesService;

/**
 * 内容得分模板Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class BizScoreTemplatesServiceImpl implements IBizScoreTemplatesService 
{
    @Autowired
    private BizScoreTemplatesMapper bizScoreTemplatesMapper;

    /**
     * 查询内容得分模板
     * 
     * @param templatesId 内容得分模板主键
     * @return 内容得分模板
     */
    @Override
    public BizScoreTemplates selectBizScoreTemplatesByTemplatesId(Long templatesId)
    {
        return bizScoreTemplatesMapper.selectBizScoreTemplatesByTemplatesId(templatesId);
    }

    /**
     * 查询内容得分模板列表
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 内容得分模板
     */
    @Override
    public List<BizScoreTemplates> selectBizScoreTemplatesList(BizScoreTemplates bizScoreTemplates)
    {
        return bizScoreTemplatesMapper.selectBizScoreTemplatesList(bizScoreTemplates);
    }

    /**
     * 新增内容得分模板
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 结果
     */
    @Override
    public int insertBizScoreTemplates(BizScoreTemplates bizScoreTemplates)
    {
        return bizScoreTemplatesMapper.insertBizScoreTemplates(bizScoreTemplates);
    }

    /**
     * 修改内容得分模板
     * 
     * @param bizScoreTemplates 内容得分模板
     * @return 结果
     */
    @Override
    public int updateBizScoreTemplates(BizScoreTemplates bizScoreTemplates)
    {
        return bizScoreTemplatesMapper.updateBizScoreTemplates(bizScoreTemplates);
    }

    /**
     * 批量删除内容得分模板
     * 
     * @param templatesIds 需要删除的内容得分模板主键
     * @return 结果
     */
    @Override
    public int deleteBizScoreTemplatesByTemplatesIds(Long[] templatesIds)
    {
        return bizScoreTemplatesMapper.deleteBizScoreTemplatesByTemplatesIds(templatesIds);
    }

    /**
     * 删除内容得分模板信息
     * 
     * @param templatesId 内容得分模板主键
     * @return 结果
     */
    @Override
    public int deleteBizScoreTemplatesByTemplatesId(Long templatesId)
    {
        return bizScoreTemplatesMapper.deleteBizScoreTemplatesByTemplatesId(templatesId);
    }
}
