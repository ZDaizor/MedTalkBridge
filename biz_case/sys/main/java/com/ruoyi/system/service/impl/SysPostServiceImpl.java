package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;

/**
 * 岗位信息Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysPostServiceImpl implements ISysPostService 
{
    @Autowired
    private SysPostMapper sysPostMapper;

    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 岗位信息
     */
    @Override
    public SysPost selectSysPostByPostId(Long postId)
    {
        return sysPostMapper.selectSysPostByPostId(postId);
    }

    /**
     * 查询岗位信息列表
     * 
     * @param sysPost 岗位信息
     * @return 岗位信息
     */
    @Override
    public List<SysPost> selectSysPostList(SysPost sysPost)
    {
        return sysPostMapper.selectSysPostList(sysPost);
    }

    /**
     * 新增岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    @Override
    public int insertSysPost(SysPost sysPost)
    {
        sysPost.setCreateTime(DateUtils.getNowDate());
        return sysPostMapper.insertSysPost(sysPost);
    }

    /**
     * 修改岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    @Override
    public int updateSysPost(SysPost sysPost)
    {
        sysPost.setUpdateTime(DateUtils.getNowDate());
        return sysPostMapper.updateSysPost(sysPost);
    }

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位信息主键
     * @return 结果
     */
    @Override
    public int deleteSysPostByPostIds(Long[] postIds)
    {
        return sysPostMapper.deleteSysPostByPostIds(postIds);
    }

    /**
     * 删除岗位信息信息
     * 
     * @param postId 岗位信息主键
     * @return 结果
     */
    @Override
    public int deleteSysPostByPostId(Long postId)
    {
        return sysPostMapper.deleteSysPostByPostId(postId);
    }
}
