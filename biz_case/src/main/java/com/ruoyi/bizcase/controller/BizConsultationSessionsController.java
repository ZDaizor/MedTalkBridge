package com.ruoyi.bizcase.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.SystemUtils;
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
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.ruoyi.bizcase.domain.dto.StartTrainingDTO;
import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.service.IBizCaseService;

/**
 * 学生问诊会话Controller
 *
 * @author daizor
 * @date 2025-09-04
 */
@Api(value = "学生问诊会话管理", tags = "学生问诊会话相关接口")
@RestController
@RequestMapping("/system/sessions")
public class BizConsultationSessionsController extends BaseController {
    @Autowired
    private IBizConsultationSessionsService bizConsultationSessionsService;

    @Autowired
    private IBizCaseService bizCaseService;

    /**
     * 查询学生问诊会话列表
     */
    @ApiOperation(value = "查询学生问诊会话列表", notes = "分页查询学生问诊会话信息")
    @PreAuthorize("@ss.hasPermi('system:sessions:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizConsultationSessions bizConsultationSessions) {
        startPage();
        List<BizConsultationSessions> list =
                bizConsultationSessionsService.selectBizConsultationSessionsList(bizConsultationSessions);
        return getDataTable(list);
    }

    /**
     * 导出学生问诊会话列表
     */
    @ApiOperation(value = "导出学生问诊会话列表", notes = "导出所有学生问诊会话信息为Excel")
    @PreAuthorize("@ss.hasPermi('system:sessions:export')")
    @Log(title = "学生问诊会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizConsultationSessions bizConsultationSessions) {
        List<BizConsultationSessions> list =
                bizConsultationSessionsService.selectBizConsultationSessionsList(bizConsultationSessions);
        ExcelUtil<BizConsultationSessions> util = new ExcelUtil<BizConsultationSessions>(BizConsultationSessions.class);
        util.exportExcel(response, list, "学生问诊会话数据");
    }

    /**
     * 获取学生问诊会话详细信息
     */
    @ApiOperation(value = "获取学生问诊会话详细信息", notes = "根据sessionId获取学生问诊会话详情")
    @ApiImplicitParam(name = "sessionId", value = "会话ID", required = true, dataType = "String", paramType = "path")
    @PreAuthorize("@ss.hasPermi('system:sessions:query')")
    @GetMapping(value = "/{sessionId}")
    public AjaxResult getInfo(@PathVariable("sessionId") String sessionId) {
        return success(bizConsultationSessionsService.selectBizConsultationSessionsBySessionId(sessionId));
    }

    /**
     * 新增学生问诊会话
     */
    @ApiOperation(value = "新增学生问诊会话", notes = "添加新的学生问诊会话记录")
    @PreAuthorize("@ss.hasPermi('system:sessions:add')")
    @Log(title = "学生问诊会话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@ApiParam(name = "bizConsultationSessions", value = "学生问诊会话实体", required = true) @RequestBody BizConsultationSessions bizConsultationSessions) {
        return toAjax(bizConsultationSessionsService.insertBizConsultationSessions(bizConsultationSessions));
    }

    /**
     * 修改学生问诊会话
     */
    @ApiOperation(value = "修改学生问诊会话", notes = "根据主键修改学生问诊会话信息")
    @PreAuthorize("@ss.hasPermi('system:sessions:edit')")
    @Log(title = "学生问诊会话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@ApiParam(name = "bizConsultationSessions", value = "学生问诊会话实体", required = true) @RequestBody BizConsultationSessions bizConsultationSessions) {
        return toAjax(bizConsultationSessionsService.updateBizConsultationSessions(bizConsultationSessions));
    }

    /**
     * 删除学生问诊会话
     */
    @ApiOperation(value = "删除学生问诊会话", notes = "根据主键批量删除学生问诊会话")
    @ApiImplicitParam(name = "sessionIds", value = "会话ID数组", required = true, dataType = "String", allowMultiple =
            true, paramType = "path")
    @PreAuthorize("@ss.hasPermi('system:sessions:remove')")
    @Log(title = "学生问诊会话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sessionIds}")
    public AjaxResult remove(@PathVariable String[] sessionIds) {
        return toAjax(bizConsultationSessionsService.deleteBizConsultationSessionsBySessionIds(sessionIds));
    }

    /**
     * 开始训练，新增学生问诊记录
     */
    @ApiOperation(value = "开始训练", notes = "新增学生问诊记录，自动补全病例标题和模拟患者姓名")
    @PreAuthorize("@ss.hasPermi('system:sessions:add')")
    @PostMapping("/start")
    public AjaxResult startTraining(@RequestBody StartTrainingDTO dto) {
        // 查询病例信息
        Long userId = SecurityUtils.getUserId();
        BizCase bizCase = bizCaseService.selectBizCaseByCaseId(Long.valueOf(dto.getCaseId()));
        if (bizCase == null) {
            return error("未找到对应的病例信息");
        }
        BizConsultationSessions session = new BizConsultationSessions();
        session.setUserId(userId);
        session.setCaseId(dto.getCaseId());
        session.setStepId(dto.getStepId());
        session.setCaseTitle(bizCase.getCaseName());
        session.setPatientName(bizCase.getPatientName());
        session.setCreateTime(new java.util.Date());
        session.setCreateBy(SecurityUtils.getUsername());
        int result = bizConsultationSessionsService.insertBizConsultationSessions(session);
        return toAjax(result);
    }


}
