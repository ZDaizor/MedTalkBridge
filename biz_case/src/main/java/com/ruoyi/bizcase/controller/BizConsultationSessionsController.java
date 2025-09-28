package com.ruoyi.bizcase.controller;

import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.domain.BizConsultationSeMessages;
import com.ruoyi.bizcase.domain.BizCaseEvaluations;
import com.ruoyi.bizcase.domain.BizEvScoreDetails;
import com.ruoyi.bizcase.domain.dto.StartTrainingDTO;
import com.ruoyi.bizcase.service.IBizCaseService;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;
import com.ruoyi.bizcase.service.IBizConsultationSeMessagesService;
import com.ruoyi.bizcase.service.IBizCaseEvaluationsService;
import com.ruoyi.bizcase.service.IBizEvScoreDetailsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.math.BigDecimal;
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

    @Autowired
    private IBizCaseEvaluationsService bizCaseEvaluationsService;

    @Autowired
    private IBizEvScoreDetailsService bizEvScoreDetailsService;

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
    @PreAuthorize("@ss.hasPermi('system:sessions:start')")
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
        session.setEvalMode(dto.getEvalMode());
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

            // 解析并保存评分数据
            Long evaluationId = saveEvaluationData(evaluationResult, session);
            if (evaluationId != null) {
                logger.info("会话ID[{}]的评分数据保存完成，evaluationId: {}", sessionId, evaluationId);
            } else {
                logger.error("会话ID[{}]的评分数据保存失败", sessionId);
            }

        } catch (Exception e) {
            logger.error("调用Dify评分失败，sessionId={}, error={}", sessionId, e.getMessage(), e);
        }
        return toAjax(result);
    }

    /**
     * 保存评分数据到数据库
     * 先在总分表中创建记录，再在分项表中保存详情
     *
     * @param difyResult Dify返回的评分JSON结果
     * @param session 会话对象
     * @return 评分记录ID
     */
    private Long saveEvaluationData(String difyResult, BizConsultationSessions session) {
        try {
            // 解析Dify返回的JSON数据
            JSONArray scoreArray = JSON.parseArray(difyResult);
            if (scoreArray == null || scoreArray.isEmpty()) {
                logger.error("解析Dify评分结果失败，数据为空");
                return null;
            }

            // 计算总分和满分
            BigDecimal totalScore = BigDecimal.ZERO;
            BigDecimal totalMaxScore = BigDecimal.ZERO;

            for (int i = 0; i < scoreArray.size(); i++) {
                JSONObject scoreItem = scoreArray.getJSONObject(i);
                if (scoreItem.containsKey("scored") && scoreItem.containsKey("score")) {
                    try {
                        BigDecimal scored = parseScoreSafely(scoreItem.getString("scored"));
                        BigDecimal maxScore = parseScoreSafely(scoreItem.getString("score"));
                        totalScore = totalScore.add(scored);
                        totalMaxScore = totalMaxScore.add(maxScore);
                    } catch (Exception e) {
                        logger.warn("解析第{}个评分项时发生错误: {}", i + 1, e.getMessage());
                    }
                }
            }

            // 1. 先检查是否已存在评分记录（根据唯一键约束：user_id, case_id, step_id）
            BizCaseEvaluations queryCondition = new BizCaseEvaluations();
            queryCondition.setUserId(session.getUserId());
            queryCondition.setCaseId(session.getCaseId());
            queryCondition.setStepId(session.getStepId().longValue());

            List<BizCaseEvaluations> existingEvaluations = bizCaseEvaluationsService.selectBizCaseEvaluationsList(queryCondition);

            BizCaseEvaluations evaluation;
            Long evaluationId;

            if (existingEvaluations != null && !existingEvaluations.isEmpty()) {
                // 如果已存在，则更新现有记录
                evaluation = existingEvaluations.get(0);
                evaluationId = evaluation.getEvaluationId();

                evaluation.setTotleScore(totalScore);
                evaluation.setTotleMaxScore(totalMaxScore);
                evaluation.setSessionId(Long.valueOf(session.getSessionId()));
                evaluation.setEvalMode(1L);

                int updateResult = bizCaseEvaluationsService.updateBizCaseEvaluations(evaluation);
                if (updateResult <= 0) {
                    logger.error("更新总分表失败");
                    return null;
                }
                logger.info("成功更新总分记录，evaluationId: {}, 总分: {}/{}", evaluationId, totalScore, totalMaxScore);

                // 删除旧的分项记录
                BizEvScoreDetails deleteCondition = new BizEvScoreDetails();
                deleteCondition.setEvaluationId(evaluationId);
                List<BizEvScoreDetails> oldDetails = bizEvScoreDetailsService.selectBizEvScoreDetailsList(deleteCondition);
                if (oldDetails != null && !oldDetails.isEmpty()) {
                    for (BizEvScoreDetails oldDetail : oldDetails) {
                        bizEvScoreDetailsService.deleteBizEvScoreDetailsByScoreId(oldDetail.getScoreId());
                    }
                    logger.info("删除了{}条旧的评分详情记录", oldDetails.size());
                }

            } else {
                // 如果不存在，则创建新记录
                evaluation = new BizCaseEvaluations();
                evaluation.setUserId(session.getUserId());
                evaluation.setCaseId(session.getCaseId());
                evaluation.setStepId(session.getStepId().longValue());
                evaluation.setSessionId(Long.valueOf(session.getSessionId()));
                evaluation.setTotleScore(totalScore);
                evaluation.setTotleMaxScore(totalMaxScore);
                evaluation.setEvalMode(1L); // 1：训练模式

                int insertResult = bizCaseEvaluationsService.insertBizCaseEvaluations(evaluation);
                if (insertResult <= 0) {
                    logger.error("插入总分表失败");
                    return null;
                }

                evaluationId = evaluation.getEvaluationId();
                logger.info("成功创建总分记录，evaluationId: {}, 总分: {}/{}", evaluationId, totalScore, totalMaxScore);
            }

            // 2. 再创建分项表记录
            int savedDetailsCount = 0;
            for (int i = 0; i < scoreArray.size(); i++) {
                JSONObject scoreItem = scoreArray.getJSONObject(i);

                try {
                    BizEvScoreDetails scoreDetails = new BizEvScoreDetails();
                    scoreDetails.setEvaluationId(evaluationId);

                    // order 对应 itemId
                    if (scoreItem.containsKey("order")) {
                        scoreDetails.setItemId(scoreItem.getInteger("order"));
                    }

                    // itemContent 对应 itemContent (评分项内容描述)
                    if (scoreItem.containsKey("itemContent")) {
                        scoreDetails.setItemContent(scoreItem.getString("type"));
                    }

                    // scored 对应 scoreAchieved (实际得分)
                    if (scoreItem.containsKey("scored")) {
                        String scoredStr = scoreItem.getString("scored");
                        if (scoredStr != null && !scoredStr.isEmpty()) {
                            scoreDetails.setScoreAchieved(parseScoreSafely(scoredStr));
                        }
                    }

                    // score 对应 scoreMax (该项总分)
                    if (scoreItem.containsKey("score")) {
                        String scoreStr = scoreItem.getString("score");
                        if (scoreStr != null && !scoreStr.isEmpty()) {
                            scoreDetails.setScoreMax(parseScoreSafely(scoreStr));
                        }
                    }

                    // 设置分数类型为 AI评分 (1)
                    scoreDetails.setScoreType(1);

                    // basis 对应 scoreBasis (评分依据)
                    if (scoreItem.containsKey("basis")) {
                        scoreDetails.setScoreBasis(scoreItem.getString("basis"));
                    }

                    // 保存到数据库
                    int detailResult = bizEvScoreDetailsService.insertBizEvScoreDetails(scoreDetails);
                    if (detailResult > 0) {
                        savedDetailsCount++;
                        logger.debug("成功保存评分详情，itemId: {}, scoreAchieved: {}, scoreMax: {}",
                                scoreDetails.getItemId(), scoreDetails.getScoreAchieved(), scoreDetails.getScoreMax());
                    } else {
                        logger.warn("保存评分详情失败，itemId: {}", scoreDetails.getItemId());
                    }

                } catch (Exception e) {
                    logger.error("处理第{}个评分项时发生错误: {}", i + 1, e.getMessage(), e);
                }
            }

            logger.info("评分详情保存完成，成功保存{}条记录", savedDetailsCount);
            return evaluationId;

        } catch (Exception e) {
            logger.error("保存评分数据失败: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 安全地解析分数字符串为BigDecimal
     * 如果不能解析为数字，则返回0
     *
     * @param scoreStr 分数字符串
     * @return BigDecimal分数
     */
    private BigDecimal parseScoreSafely(String scoreStr) {
        if (scoreStr == null || scoreStr.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        // 去除空格和特殊字符
        String cleanStr = scoreStr.trim();

        // 如果是非数字字符串（如"未提及"、"无"等），返回0
        if (!isNumeric(cleanStr)) {
            logger.debug("非数字分数值: {}，转换为0", scoreStr);
            return BigDecimal.ZERO;
        }

        try {
            return new BigDecimal(cleanStr);
        } catch (NumberFormatException e) {
            logger.warn("无法解析分数字符串: {}，使用0代替", scoreStr);
            return BigDecimal.ZERO;
        }
    }

    /**
     * 检查字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
