package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRoleMenuMapper;
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.service.ISysRoleMenuService;

/**
 * 角色和菜单关联Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService 
{
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 查询角色和菜单关联
     * 
     * @param roleId 角色和菜单关联主键
     * @return 角色和菜单关联
     */
    @Override
    public SysRoleMenu selectSysRoleMenuByRoleId(Long roleId)
    {
        return sysRoleMenuMapper.selectSysRoleMenuByRoleId(roleId);
    }

    /**
     * 查询角色和菜单关联列表
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 角色和菜单关联
     */
    @Override
    public List<SysRoleMenu> selectSysRoleMenuList(SysRoleMenu sysRoleMenu)
    {
        return sysRoleMenuMapper.selectSysRoleMenuList(sysRoleMenu);
    }

    /**
     * 新增角色和菜单关联
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 结果
     */
    @Override
    public int insertSysRoleMenu(SysRoleMenu sysRoleMenu)
    {
        return sysRoleMenuMapper.insertSysRoleMenu(sysRoleMenu);
    }

    /**
     * 修改角色和菜单关联
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 结果
     */
    @Override
    public int updateSysRoleMenu(SysRoleMenu sysRoleMenu)
    {
        return sysRoleMenuMapper.updateSysRoleMenu(sysRoleMenu);
    }

    /**
     * 批量删除角色和菜单关联
     * 
     * @param roleIds 需要删除的角色和菜单关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleMenuByRoleIds(Long[] roleIds)
    {
        return sysRoleMenuMapper.deleteSysRoleMenuByRoleIds(roleIds);
    }

    /**
     * 删除角色和菜单关联信息
     * 
     * @param roleId 角色和菜单关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleMenuByRoleId(Long roleId)
    {
        return sysRoleMenuMapper.deleteSysRoleMenuByRoleId(roleId);
    }
}
