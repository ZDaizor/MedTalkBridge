package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPost;

/**
 * 岗位信息Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysPostMapper 
{
    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 岗位信息
     */
    public SysPost selectSysPostByPostId(Long postId);

    /**
     * 查询岗位信息列表
     * 
     * @param sysPost 岗位信息
     * @return 岗位信息集合
     */
    public List<SysPost> selectSysPostList(SysPost sysPost);

    /**
     * 新增岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int insertSysPost(SysPost sysPost);

    /**
     * 修改岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int updateSysPost(SysPost sysPost);

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 结果
     */
    public int deleteSysPostByPostId(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPostByPostIds(Long[] postIds);
}
