package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizEvScoreDetailsMapper;
import com.ruoyi.system.domain.BizEvScoreDetails;
import com.ruoyi.system.service.IBizEvScoreDetailsService;

/**
 * 内容得分详情Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class BizEvScoreDetailsServiceImpl implements IBizEvScoreDetailsService 
{
    @Autowired
    private BizEvScoreDetailsMapper bizEvScoreDetailsMapper;

    /**
     * 查询内容得分详情
     * 
     * @param scoreId 内容得分详情主键
     * @return 内容得分详情
     */
    @Override
    public BizEvScoreDetails selectBizEvScoreDetailsByScoreId(Long scoreId)
    {
        return bizEvScoreDetailsMapper.selectBizEvScoreDetailsByScoreId(scoreId);
    }

    /**
     * 查询内容得分详情列表
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 内容得分详情
     */
    @Override
    public List<BizEvScoreDetails> selectBizEvScoreDetailsList(BizEvScoreDetails bizEvScoreDetails)
    {
        return bizEvScoreDetailsMapper.selectBizEvScoreDetailsList(bizEvScoreDetails);
    }

    /**
     * 新增内容得分详情
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 结果
     */
    @Override
    public int insertBizEvScoreDetails(BizEvScoreDetails bizEvScoreDetails)
    {
        return bizEvScoreDetailsMapper.insertBizEvScoreDetails(bizEvScoreDetails);
    }

    /**
     * 修改内容得分详情
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 结果
     */
    @Override
    public int updateBizEvScoreDetails(BizEvScoreDetails bizEvScoreDetails)
    {
        return bizEvScoreDetailsMapper.updateBizEvScoreDetails(bizEvScoreDetails);
    }

    /**
     * 批量删除内容得分详情
     * 
     * @param scoreIds 需要删除的内容得分详情主键
     * @return 结果
     */
    @Override
    public int deleteBizEvScoreDetailsByScoreIds(Long[] scoreIds)
    {
        return bizEvScoreDetailsMapper.deleteBizEvScoreDetailsByScoreIds(scoreIds);
    }

    /**
     * 删除内容得分详情信息
     * 
     * @param scoreId 内容得分详情主键
     * @return 结果
     */
    @Override
    public int deleteBizEvScoreDetailsByScoreId(Long scoreId)
    {
        return bizEvScoreDetailsMapper.deleteBizEvScoreDetailsByScoreId(scoreId);
    }
}
