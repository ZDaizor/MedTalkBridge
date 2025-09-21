package com.ruoyi.bizcase.controller;

import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.domain.BizConsultationSeMessages;
import com.ruoyi.bizcase.domain.dto.StartTrainingDTO;
import com.ruoyi.bizcase.service.IBizCaseService;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;
import com.ruoyi.bizcase.service.IBizConsultationSeMessagesService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    private static final Logger logger = LoggerFactory.getLogger(BizConsultationSessionsController.class);

    @Autowired
    private IBizConsultationSessionsService bizConsultationSessionsService;

    @Autowired
    private IBizCaseService bizCaseService;

    @Autowired
    private IBizConsultationSeMessagesService bizConsultationSeMessagesService;

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
    public void export(HttpServletResponse response,
                       @ApiParam(name = "bizConsultationSessions", value = "学生问诊会话实体") BizConsultationSessions bizConsultationSessions) {
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
    public AjaxResult getInfo(@ApiParam(name = "sessionId", value = "会话ID", required = true) @PathVariable("sessionId"
    ) String sessionId) {
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
        Long sessionId = bizConsultationSessionsService.insertBizConsultationSessions(bizConsultationSessions);
        return AjaxResult.success(sessionId);
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
    public AjaxResult remove(@ApiParam(name = "sessionIds", value = "会话ID数组", required = true) @PathVariable String[] sessionIds) {
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
        Long sessionId = bizConsultationSessionsService.insertBizConsultationSessions(session);
        return AjaxResult.success(sessionId);
    }

    /**
     * 结束训练，更新学生问诊会话状态为已完成
     */
    @ApiOperation(value = "结束训练", notes = "将学生问诊会话状态设为已完成，并记录结束时间")
    @PreAuthorize("@ss.hasPermi('system:sessions:edit')")
    @PostMapping("/finish/{sessionId}")
    public AjaxResult finishTraining(@ApiParam(name = "sessionId", value = "会话ID", required = true) @PathVariable(
            "sessionId") String sessionId) {
        BizConsultationSessions session =
                bizConsultationSessionsService.selectBizConsultationSessionsBySessionId(sessionId);
        if (session == null) {
            return error("未找到对应的会话信息");
        }
        session.setStatus(1); // 1代表已完成
        session.setEndTime(new java.util.Date());
        int result = bizConsultationSessionsService.updateBizConsultationSessions(session);

        // 查询当前会话的所有对话记录
        BizConsultationSeMessages queryCondition = new BizConsultationSeMessages();
        queryCondition.setSessionId(sessionId);
        List<BizConsultationSeMessages> conversationRecords =
                bizConsultationSeMessagesService.selectBizConsultationSeMessagesList(queryCondition);

        // 打印对话记录日志
        logger.info("查询到会话ID[{}]的对话记录数量: {}", sessionId, conversationRecords.size());
        for (BizConsultationSeMessages message : conversationRecords) {
            logger.info("对话记录 - 消息ID: {}, 发送者类型: {}, 消息顺序: {}, 消息内容: {}, 发送时间: {}",
                    message.getMessagesId(),
                    message.getSenderType() == 1 ? "患者" : "学生",
                    message.getMessageOrder(),
                    message.getMessageContent(),
                    message.getTimestamp());
        }

        try {
            String evaluationResult = bizConsultationSessionsService.evaluateSessionWithDify(
                    sessionId, session.getCaseId(), session.getStepId().longValue());
            logger.info("会话ID[{}]的Dify评分结果: {}", sessionId, evaluationResult);

            // 解析并保存评分详情（暂时使用sessionId作为evaluationId，实际项目中需要传入真实的evaluationId）
            try {
                Long evaluationId = Long.valueOf(sessionId); // todo daizor 这里需要传入真实的evaluationId
                int savedCount = bizConsultationSessionsService.saveDifyScoreDetails(evaluationResult, evaluationId);
                logger.info("会话ID[{}]的评分详情保存完成，共保存{}*条记录", sessionId, savedCount);
            } catch (Exception saveEx) {
                logger.error("保存评分详情失败，sessionId={}, error={}", sessionId, saveEx.getMessage(), saveEx);
            }

        } catch (Exception e) {
            logger.error("调用Dify评分失败，sessionId={}, error={}", sessionId, e.getMessage(), e);
        }
        return toAjax(result);
    }
}
