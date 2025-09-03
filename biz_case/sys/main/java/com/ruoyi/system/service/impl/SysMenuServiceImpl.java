package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 菜单权限Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService 
{
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询菜单权限
     * 
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    @Override
    public SysMenu selectSysMenuByMenuId(Long menuId)
    {
        return sysMenuMapper.selectSysMenuByMenuId(menuId);
    }

    /**
     * 查询菜单权限列表
     * 
     * @param sysMenu 菜单权限
     * @return 菜单权限
     */
    @Override
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu)
    {
        return sysMenuMapper.selectSysMenuList(sysMenu);
    }

    /**
     * 新增菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    @Override
    public int insertSysMenu(SysMenu sysMenu)
    {
        sysMenu.setCreateTime(DateUtils.getNowDate());
        return sysMenuMapper.insertSysMenu(sysMenu);
    }

    /**
     * 修改菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu)
    {
        sysMenu.setUpdateTime(DateUtils.getNowDate());
        return sysMenuMapper.updateSysMenu(sysMenu);
    }

    /**
     * 批量删除菜单权限
     * 
     * @param menuIds 需要删除的菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuIds(Long[] menuIds)
    {
        return sysMenuMapper.deleteSysMenuByMenuIds(menuIds);
    }

    /**
     * 删除菜单权限信息
     * 
     * @param menuId 菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuId(Long menuId)
    {
        return sysMenuMapper.deleteSysMenuByMenuId(menuId);
    }
}
