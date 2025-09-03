package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleMenu;

/**
 * 角色和菜单关联Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysRoleMenuMapper 
{
    /**
     * 查询角色和菜单关联
     * 
     * @param roleId 角色和菜单关联主键
     * @return 角色和菜单关联
     */
    public SysRoleMenu selectSysRoleMenuByRoleId(Long roleId);

    /**
     * 查询角色和菜单关联列表
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 角色和菜单关联集合
     */
    public List<SysRoleMenu> selectSysRoleMenuList(SysRoleMenu sysRoleMenu);

    /**
     * 新增角色和菜单关联
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 结果
     */
    public int insertSysRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     * 修改角色和菜单关联
     * 
     * @param sysRoleMenu 角色和菜单关联
     * @return 结果
     */
    public int updateSysRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     * 删除角色和菜单关联
     * 
     * @param roleId 角色和菜单关联主键
     * @return 结果
     */
    public int deleteSysRoleMenuByRoleId(Long roleId);

    /**
     * 批量删除角色和菜单关联
     * 
     * @param roleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysRoleMenuByRoleIds(Long[] roleIds);
}
