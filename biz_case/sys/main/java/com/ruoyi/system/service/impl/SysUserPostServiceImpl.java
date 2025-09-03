package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.service.ISysUserPostService;

/**
 * 用户与岗位关联Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysUserPostServiceImpl implements ISysUserPostService 
{
    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     * 查询用户与岗位关联
     * 
     * @param userId 用户与岗位关联主键
     * @return 用户与岗位关联
     */
    @Override
    public SysUserPost selectSysUserPostByUserId(Long userId)
    {
        return sysUserPostMapper.selectSysUserPostByUserId(userId);
    }

    /**
     * 查询用户与岗位关联列表
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 用户与岗位关联
     */
    @Override
    public List<SysUserPost> selectSysUserPostList(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.selectSysUserPostList(sysUserPost);
    }

    /**
     * 新增用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    @Override
    public int insertSysUserPost(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.insertSysUserPost(sysUserPost);
    }

    /**
     * 修改用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    @Override
    public int updateSysUserPost(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.updateSysUserPost(sysUserPost);
    }

    /**
     * 批量删除用户与岗位关联
     * 
     * @param userIds 需要删除的用户与岗位关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserPostByUserIds(Long[] userIds)
    {
        return sysUserPostMapper.deleteSysUserPostByUserIds(userIds);
    }

    /**
     * 删除用户与岗位关联信息
     * 
     * @param userId 用户与岗位关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserPostByUserId(Long userId)
    {
        return sysUserPostMapper.deleteSysUserPostByUserId(userId);
    }
}
