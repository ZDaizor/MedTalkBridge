package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysMenu;

/**
 * 菜单权限Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysMenuMapper 
{
    /**
     * 查询菜单权限
     * 
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    public SysMenu selectSysMenuByMenuId(Long menuId);

    /**
     * 查询菜单权限列表
     * 
     * @param sysMenu 菜单权限
     * @return 菜单权限集合
     */
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu);

    /**
     * 新增菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    public int insertSysMenu(SysMenu sysMenu);

    /**
     * 修改菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    public int updateSysMenu(SysMenu sysMenu);

    /**
     * 删除菜单权限
     * 
     * @param menuId 菜单权限主键
     * @return 结果
     */
    public int deleteSysMenuByMenuId(Long menuId);

    /**
     * 批量删除菜单权限
     * 
     * @param menuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMenuByMenuIds(Long[] menuIds);
}
