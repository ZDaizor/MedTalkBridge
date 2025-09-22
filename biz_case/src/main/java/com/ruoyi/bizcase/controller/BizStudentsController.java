package com.ruoyi.bizcase.controller;

import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.service.IBizCaseService;
import com.ruoyi.bizcase.service.IBizStudentsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 病例Controller
 *
 * @author daizor
 * @date 2025-09-04
 */
@RestController
@RequestMapping("/system/student")
public class BizStudentsController extends BaseController {
    @Autowired
    private IBizStudentsService bizStudentsService;

    /**
     * 查询学生已完成全部训练病历的总数
     */
    @ApiOperation("查询学生已完成全部训练病历的总数")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/completed/count/{userId}")
    public AjaxResult getCompletedCaseCount(@PathVariable Long userId) {
        int count = bizStudentsService.countCompletedCasesByUserId(userId);
        return AjaxResult.success(count);
    }

    /**
     * 查询用户训练模式下的总学习时长（分钟）
     */
    @ApiOperation("查询学生训练模式下的总学习时长（分钟）")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/training/totalMinutes/{userId}")
    public AjaxResult getTrainingTotalMinutes(@PathVariable Long userId) {
        int totalMinutes = bizStudentsService.sumTrainingMinutesByUserId(userId);
        return AjaxResult.success(totalMinutes);
    }

    /**
     * 查询学生首页统计信息：训练数量、学习时长、平均得分
     */
    @ApiOperation("查询学生首页统计信息：训练数量、学习时长、平均得分")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/index/selects/{userId}")
    public AjaxResult getIndexSelects(@PathVariable Long userId) {
        int completedCount = bizStudentsService.countCompletedCasesByUserId(userId);
        int totalMinutes = bizStudentsService.sumTrainingMinutesByUserId(userId);
        Double averageScore = bizStudentsService.getAverageScoreByUserId(userId);
        // 组装返回数据
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("completedCount", completedCount);
        result.put("totalMinutes", totalMinutes);
        result.put("averageScore", averageScore);
        return AjaxResult.success(result);
    }

    /**
     * 查询学生训练统计信息：训练次数、总训练时长、平均得分
     */
    @ApiOperation("查询学生训练统计信息：训练次数、总训练时长、平均得分")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/trainingUp/stat/{userId}")
    public AjaxResult getTrainingUp(@PathVariable Long userId) {
        int trainingCount = bizStudentsService.countCompletedCasesByUserId(userId);
        int totalMinutes = bizStudentsService.sumTrainingMinutesByUserId(userId);
        Double averageScore = bizStudentsService.getAverageScoreByUserId(userId);
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("trainingCount", trainingCount);
        result.put("totalMinutes", totalMinutes);
        result.put("averageScore", averageScore);
        return AjaxResult.success(result);
    }

    /**
     * 查询学生训练明细列表
     */
    @ApiOperation("查询学生训练明细列表")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/training/list/{userId}")
    public TableDataInfo getTrainingList(@PathVariable Long userId,
                                         @RequestParam(required = false) String caseName) {
        startPage();
        List<Map<String, Object>> list = bizStudentsService.getTrainingList(userId,caseName);
        return getDataTable(list);
    }

    /**
     * 查询学生活跃列表
     */
    @ApiOperation("查询学生活跃列表")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/active/list/{userId}")
    public AjaxResult getStudentActive(@PathVariable Long userId) {
        List<Map<String, Object>> list = bizStudentsService.getActivityList(userId);
        return AjaxResult.success(list);
    }

    /**
     * 查询学生考试统计信息：考试次数、总考试时长、平均得分、A级次数
     */
    @ApiOperation("查询学生考试统计信息：考试次数、总考试时长、平均得分、A级次数")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/examUp/stat/{userId}")
    public AjaxResult getExamUp(@PathVariable Long userId) {
        int examCount = bizStudentsService.countExamSessionsByUserId(userId);
        int totalMinutes = bizStudentsService.sumExamMinutesByUserId(userId);
        Double averageScore = bizStudentsService.getAverageScoreExamByUserId(userId);
        int aLevelCount = bizStudentsService.countHighScoreExamSessionsByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("examCount", examCount);
        result.put("totalMinutes", totalMinutes);
        result.put("averageScore", averageScore);
        result.put("aLevelCount", aLevelCount);
        return AjaxResult.success(result);
    }

    /**
     * 查询学生考试明细列表
     */
    @ApiOperation("查询学生考试明细列表")
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/exam/list/{userId}")
    public AjaxResult getExamList(@PathVariable Long userId,
                                  @RequestParam(required = false) String caseName) {
        List<Map<String, Object>> list = bizStudentsService.getExamList(userId,caseName);
        return AjaxResult.success(list);
    }

}
