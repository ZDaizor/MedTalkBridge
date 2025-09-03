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
import com.ruoyi.system.domain.SysJobLog;
import com.ruoyi.system.service.ISysJobLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 定时任务调度日志Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/log")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService sysJobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJobLog sysJobLog)
    {
        startPage();
        List<SysJobLog> list = sysJobLogService.selectSysJobLogList(sysJobLog);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobLog sysJobLog)
    {
        List<SysJobLog> list = sysJobLogService.selectSysJobLogList(sysJobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        util.exportExcel(response, list, "定时任务调度日志数据");
    }

    /**
     * 获取定时任务调度日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{jobLogId}")
    public AjaxResult getInfo(@PathVariable("jobLogId") Long jobLogId)
    {
        return success(sysJobLogService.selectSysJobLogByJobLogId(jobLogId));
    }

    /**
     * 新增定时任务调度日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJobLog sysJobLog)
    {
        return toAjax(sysJobLogService.insertSysJobLog(sysJobLog));
    }

    /**
     * 修改定时任务调度日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJobLog sysJobLog)
    {
        return toAjax(sysJobLogService.updateSysJobLog(sysJobLog));
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(sysJobLogService.deleteSysJobLogByJobLogIds(jobLogIds));
    }
}
