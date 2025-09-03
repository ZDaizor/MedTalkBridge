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
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysUserRoleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和角色关联Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/role")
public class SysUserRoleController extends BaseController
{
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 查询用户和角色关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserRole sysUserRole)
    {
        startPage();
        List<SysUserRole> list = sysUserRoleService.selectSysUserRoleList(sysUserRole);
        return getDataTable(list);
    }

    /**
     * 导出用户和角色关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:export')")
    @Log(title = "用户和角色关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserRole sysUserRole)
    {
        List<SysUserRole> list = sysUserRoleService.selectSysUserRoleList(sysUserRole);
        ExcelUtil<SysUserRole> util = new ExcelUtil<SysUserRole>(SysUserRole.class);
        util.exportExcel(response, list, "用户和角色关联数据");
    }

    /**
     * 获取用户和角色关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserRoleService.selectSysUserRoleByUserId(userId));
    }

    /**
     * 新增用户和角色关联
     */
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @Log(title = "用户和角色关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserRole sysUserRole)
    {
        return toAjax(sysUserRoleService.insertSysUserRole(sysUserRole));
    }

    /**
     * 修改用户和角色关联
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "用户和角色关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserRole sysUserRole)
    {
        return toAjax(sysUserRoleService.updateSysUserRole(sysUserRole));
    }

    /**
     * 删除用户和角色关联
     */
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @Log(title = "用户和角色关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserRoleService.deleteSysUserRoleByUserIds(userIds));
    }
}
