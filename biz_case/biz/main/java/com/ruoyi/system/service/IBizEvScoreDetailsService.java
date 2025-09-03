package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizEvScoreDetails;

/**
 * 内容得分详情Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface IBizEvScoreDetailsService 
{
    /**
     * 查询内容得分详情
     * 
     * @param scoreId 内容得分详情主键
     * @return 内容得分详情
     */
    public BizEvScoreDetails selectBizEvScoreDetailsByScoreId(Long scoreId);

    /**
     * 查询内容得分详情列表
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 内容得分详情集合
     */
    public List<BizEvScoreDetails> selectBizEvScoreDetailsList(BizEvScoreDetails bizEvScoreDetails);

    /**
     * 新增内容得分详情
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 结果
     */
    public int insertBizEvScoreDetails(BizEvScoreDetails bizEvScoreDetails);

    /**
     * 修改内容得分详情
     * 
     * @param bizEvScoreDetails 内容得分详情
     * @return 结果
     */
    public int updateBizEvScoreDetails(BizEvScoreDetails bizEvScoreDetails);

    /**
     * 批量删除内容得分详情
     * 
     * @param scoreIds 需要删除的内容得分详情主键集合
     * @return 结果
     */
    public int deleteBizEvScoreDetailsByScoreIds(Long[] scoreIds);

    /**
     * 删除内容得分详情信息
     * 
     * @param scoreId 内容得分详情主键
     * @return 结果
     */
    public int deleteBizEvScoreDetailsByScoreId(Long scoreId);
}
