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
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 岗位信息Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    @Autowired
    private ISysPostService sysPostService;

    /**
     * 查询岗位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPost sysPost)
    {
        startPage();
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        return getDataTable(list);
    }

    /**
     * 导出岗位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:export')")
    @Log(title = "岗位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost sysPost)
    {
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        util.exportExcel(response, list, "岗位信息数据");
    }

    /**
     * 获取岗位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable("postId") Long postId)
    {
        return success(sysPostService.selectSysPostByPostId(postId));
    }

    /**
     * 新增岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "岗位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPost sysPost)
    {
        return toAjax(sysPostService.insertSysPost(sysPost));
    }

    /**
     * 修改岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "岗位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPost sysPost)
    {
        return toAjax(sysPostService.updateSysPost(sysPost));
    }

    /**
     * 删除岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "岗位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(sysPostService.deleteSysPostByPostIds(postIds));
    }
}
