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
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.service.ISysLogininforService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系统访问记录Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private ISysLogininforService sysLogininforService;

    /**
     * 查询系统访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor sysLogininfor)
    {
        startPage();
        List<SysLogininfor> list = sysLogininforService.selectSysLogininforList(sysLogininfor);
        return getDataTable(list);
    }

    /**
     * 导出系统访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:export')")
    @Log(title = "系统访问记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor sysLogininfor)
    {
        List<SysLogininfor> list = sysLogininforService.selectSysLogininforList(sysLogininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, list, "系统访问记录数据");
    }

    /**
     * 获取系统访问记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(sysLogininforService.selectSysLogininforByInfoId(infoId));
    }

    /**
     * 新增系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:add')")
    @Log(title = "系统访问记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysLogininfor sysLogininfor)
    {
        return toAjax(sysLogininforService.insertSysLogininfor(sysLogininfor));
    }

    /**
     * 修改系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:edit')")
    @Log(title = "系统访问记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysLogininfor sysLogininfor)
    {
        return toAjax(sysLogininforService.updateSysLogininfor(sysLogininfor));
    }

    /**
     * 删除系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:remove')")
    @Log(title = "系统访问记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(sysLogininforService.deleteSysLogininforByInfoIds(infoIds));
    }
}
