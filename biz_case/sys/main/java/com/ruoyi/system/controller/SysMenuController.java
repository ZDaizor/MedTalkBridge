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
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 菜单权限Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询菜单权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMenu sysMenu)
    {
        startPage();
        List<SysMenu> list = sysMenuService.selectSysMenuList(sysMenu);
        return getDataTable(list);
    }

    /**
     * 导出菜单权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:export')")
    @Log(title = "菜单权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMenu sysMenu)
    {
        List<SysMenu> list = sysMenuService.selectSysMenuList(sysMenu);
        ExcelUtil<SysMenu> util = new ExcelUtil<SysMenu>(SysMenu.class);
        util.exportExcel(response, list, "菜单权限数据");
    }

    /**
     * 获取菜单权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable("menuId") Long menuId)
    {
        return success(sysMenuService.selectSysMenuByMenuId(menuId));
    }

    /**
     * 新增菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.insertSysMenu(sysMenu));
    }

    /**
     * 修改菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.updateSysMenu(sysMenu));
    }

    /**
     * 删除菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuIds}")
    public AjaxResult remove(@PathVariable Long[] menuIds)
    {
        return toAjax(sysMenuService.deleteSysMenuByMenuIds(menuIds));
    }
}
