package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 角色信息Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService 
{
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询角色信息
     * 
     * @param roleId 角色信息主键
     * @return 角色信息
     */
    @Override
    public SysRole selectSysRoleByRoleId(Long roleId)
    {
        return sysRoleMapper.selectSysRoleByRoleId(roleId);
    }

    /**
     * 查询角色信息列表
     * 
     * @param sysRole 角色信息
     * @return 角色信息
     */
    @Override
    public List<SysRole> selectSysRoleList(SysRole sysRole)
    {
        return sysRoleMapper.selectSysRoleList(sysRole);
    }

    /**
     * 新增角色信息
     * 
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public int insertSysRole(SysRole sysRole)
    {
        sysRole.setCreateTime(DateUtils.getNowDate());
        return sysRoleMapper.insertSysRole(sysRole);
    }

    /**
     * 修改角色信息
     * 
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public int updateSysRole(SysRole sysRole)
    {
        sysRole.setUpdateTime(DateUtils.getNowDate());
        return sysRoleMapper.updateSysRole(sysRole);
    }

    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色信息主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleByRoleIds(Long[] roleIds)
    {
        return sysRoleMapper.deleteSysRoleByRoleIds(roleIds);
    }

    /**
     * 删除角色信息信息
     * 
     * @param roleId 角色信息主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleByRoleId(Long roleId)
    {
        return sysRoleMapper.deleteSysRoleByRoleId(roleId);
    }
}
