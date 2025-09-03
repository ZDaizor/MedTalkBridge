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
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.service.ISysUserPostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户与岗位关联Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/post")
public class SysUserPostController extends BaseController
{
    @Autowired
    private ISysUserPostService sysUserPostService;

    /**
     * 查询用户与岗位关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserPost sysUserPost)
    {
        startPage();
        List<SysUserPost> list = sysUserPostService.selectSysUserPostList(sysUserPost);
        return getDataTable(list);
    }

    /**
     * 导出用户与岗位关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:export')")
    @Log(title = "用户与岗位关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserPost sysUserPost)
    {
        List<SysUserPost> list = sysUserPostService.selectSysUserPostList(sysUserPost);
        ExcelUtil<SysUserPost> util = new ExcelUtil<SysUserPost>(SysUserPost.class);
        util.exportExcel(response, list, "用户与岗位关联数据");
    }

    /**
     * 获取用户与岗位关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserPostService.selectSysUserPostByUserId(userId));
    }

    /**
     * 新增用户与岗位关联
     */
    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "用户与岗位关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserPost sysUserPost)
    {
        return toAjax(sysUserPostService.insertSysUserPost(sysUserPost));
    }

    /**
     * 修改用户与岗位关联
     */
    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "用户与岗位关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserPost sysUserPost)
    {
        return toAjax(sysUserPostService.updateSysUserPost(sysUserPost));
    }

    /**
     * 删除用户与岗位关联
     */
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "用户与岗位关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserPostService.deleteSysUserPostByUserIds(userIds));
    }
}
