package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.service.ISysRoleMenuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 角色和菜单关联Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/menu")
public class SysRoleMenuController extends BaseController
{
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 查询角色和菜单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRoleMenu sysRoleMenu)
    {
        startPage();
        List<SysRoleMenu> list = sysRoleMenuService.selectSysRoleMenuList(sysRoleMenu);
        return getDataTable(list);
    }

    /**
     * 导出角色和菜单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:export')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoleMenu sysRoleMenu)
    {
        List<SysRoleMenu> list = sysRoleMenuService.selectSysRoleMenuList(sysRoleMenu);
        ExcelUtil<SysRoleMenu> util = new ExcelUtil<SysRoleMenu>(SysRoleMenu.class);
        util.exportExcel(response, list, "角色和菜单关联数据");
    }

    /**
     * 获取角色和菜单关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return success(sysRoleMenuService.selectSysRoleMenuByRoleId(roleId));
    }

    /**
     * 新增角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysRoleMenu sysRoleMenu)
    {
        return toAjax(sysRoleMenuService.insertSysRoleMenu(sysRoleMenu));
    }

    /**
     * 修改角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRoleMenu sysRoleMenu)
    {
        return toAjax(sysRoleMenuService.updateSysRoleMenu(sysRoleMenu));
    }

    /**
     * 删除角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(sysRoleMenuService.deleteSysRoleMenuByRoleIds(roleIds));
    }
}
