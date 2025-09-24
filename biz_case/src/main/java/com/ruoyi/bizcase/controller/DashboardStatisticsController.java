package com.ruoyi.bizcase.controller;

import com.ruoyi.bizcase.domain.DashboardStatistics;
import com.ruoyi.bizcase.service.IDashboardStatisticsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仪表板统计信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Api(tags = "仪表板统计信息")
@RestController
@RequestMapping("/system/dashboard")
public class DashboardStatisticsController extends BaseController
{
    @Autowired
    private IDashboardStatisticsService dashboardStatisticsService;

    /**
     * 查询仪表板统计信息
     */
    @ApiOperation("查询仪表板统计信息")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/statistics")
    public AjaxResult getDashboardStatistics()
    {
        DashboardStatistics statistics = dashboardStatisticsService.getDashboardStatistics();
        return AjaxResult.success(statistics);
    }
}