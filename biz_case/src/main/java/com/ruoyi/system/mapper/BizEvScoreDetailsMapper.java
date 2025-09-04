package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizEvScoreDetails;

/**
 * 内容得分详情Mapper接口
 * 
 * @author daizor
 * @date 2025-09-04
 */
public interface BizEvScoreDetailsMapper 
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
     * 删除内容得分详情
     * 
     * @param scoreId 内容得分详情主键
     * @return 结果
     */
    public int deleteBizEvScoreDetailsByScoreId(Long scoreId);

    /**
     * 批量删除内容得分详情
     * 
     * @param scoreIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizEvScoreDetailsByScoreIds(Long[] scoreIds);
}
