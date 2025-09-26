package com.ruoyi.bizcase.controller;

import com.ruoyi.bizcase.domain.ActiveUsersWeeklyStats;
import com.ruoyi.bizcase.domain.CaseTypeDistribution;
import com.ruoyi.bizcase.domain.DashboardStatistics;
import com.ruoyi.bizcase.domain.DeptStudentStatistics;
import com.ruoyi.bizcase.domain.MonthlyTrainingTrend;
import com.ruoyi.bizcase.domain.StageAssessmentStatistics;
import com.ruoyi.bizcase.domain.TeachingDataOverview;
import com.ruoyi.bizcase.domain.dto.StudentStatisticsQueryDTO;
import com.ruoyi.bizcase.service.IActiveUsersWeeklyStatsService;
import com.ruoyi.bizcase.service.ICaseTypeDistributionService;
import com.ruoyi.bizcase.service.IDeptStudentStatisticsService;
import com.ruoyi.bizcase.service.IMonthlyTrainingTrendService;
import com.ruoyi.bizcase.service.IStageAssessmentStatisticsService;
import com.ruoyi.bizcase.service.ITeachingDataOverviewService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仪表板统计信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Api(tags = "仪表板统计信息")
@RestController
@RequestMapping("/system/dashboard")
public class DashboardStatisticsController extends BaseController {


    @Autowired
    private IDeptStudentStatisticsService deptStudentStatisticsService;
    
    @Autowired
    private ITeachingDataOverviewService teachingDataOverviewService;
    
    @Autowired
    private IMonthlyTrainingTrendService monthlyTrainingTrendService;
    
    @Autowired
    private ICaseTypeDistributionService caseTypeDistributionService;
    
    @Autowired
    private IStageAssessmentStatisticsService stageAssessmentStatisticsService;
    
    @Autowired
    private IActiveUsersWeeklyStatsService activeUsersWeeklyStatsService;


    
    /**
     * 查询部门学生统计信息
     */
    @ApiOperation("查询部门学生统计信息")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/dept-student-statistics")
    public AjaxResult getDeptStudentStatistics()
    {
        List<DeptStudentStatistics> statistics = deptStudentStatisticsService.selectDeptStudentStatistics();
        return AjaxResult.success(statistics);
    }
    
    /**
     * 根据部门名称查询部门学生统计信息
     */
    @ApiOperation("根据部门名称查询部门学生统计信息")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/dept-student-statistics-by-name")
    public AjaxResult getDeptStudentStatisticsByDeptName(@RequestParam(required = false) String deptName)
    {
        List<DeptStudentStatistics> statistics = deptStudentStatisticsService.selectDeptStudentStatisticsByDeptName(deptName);
        return AjaxResult.success(statistics);
    }
    
    /**
     * 查询教学数据总体概览
     */
    @ApiOperation("查询教学数据总体概览")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/teaching-data-overview")
    public AjaxResult getTeachingDataOverview()
    {
        TeachingDataOverview overview = teachingDataOverviewService.selectTeachingDataOverview();
        return AjaxResult.success(overview);
    }
    
    /**
     * 查询月度训练趋势
     */
    @ApiOperation("查询月度训练趋势")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/monthly-training-trend")
    public AjaxResult getMonthlyTrainingTrend()
    {
        List<MonthlyTrainingTrend> trend = monthlyTrainingTrendService.selectMonthlyTrainingTrend();
        return AjaxResult.success(trend);
    }
    
    /**
     * 查询病例类型分布
     */
    @ApiOperation("查询病例类型分布")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/case-type-distribution")
    public AjaxResult getCaseTypeDistribution()
    {
        List<CaseTypeDistribution> distribution = caseTypeDistributionService.selectCaseTypeDistribution();
        return AjaxResult.success(distribution);
    }
    
    /**
     * 查询阶段评估统计
     */
    @ApiOperation("查询阶段评估统计")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/stage-assessment-statistics")
    public AjaxResult getStageAssessmentStatistics()
    {
        List<StageAssessmentStatistics> statistics = stageAssessmentStatisticsService.selectStageAssessmentStatistics();
        return AjaxResult.success(statistics);
    }

    /**
     * 查询周活跃用户统计
     */
    @ApiOperation("查询周活跃用户统计")
    @PreAuthorize("@ss.hasPermi('system:dashboard:statistics')")
    @GetMapping("/weekly-active-users")
    public AjaxResult getWeeklyActiveUsers()
    {
        List<ActiveUsersWeeklyStats> stats = activeUsersWeeklyStatsService.selectActiveUsersWeeklyStats();
        return AjaxResult.success(stats);
    }


}