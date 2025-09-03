package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserPost;

/**
 * 用户与岗位关联Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysUserPostMapper 
{
    /**
     * 查询用户与岗位关联
     * 
     * @param userId 用户与岗位关联主键
     * @return 用户与岗位关联
     */
    public SysUserPost selectSysUserPostByUserId(Long userId);

    /**
     * 查询用户与岗位关联列表
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 用户与岗位关联集合
     */
    public List<SysUserPost> selectSysUserPostList(SysUserPost sysUserPost);

    /**
     * 新增用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    public int insertSysUserPost(SysUserPost sysUserPost);

    /**
     * 修改用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    public int updateSysUserPost(SysUserPost sysUserPost);

    /**
     * 删除用户与岗位关联
     * 
     * @param userId 用户与岗位关联主键
     * @return 结果
     */
    public int deleteSysUserPostByUserId(Long userId);

    /**
     * 批量删除用户与岗位关联
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserPostByUserIds(Long[] userIds);
}
