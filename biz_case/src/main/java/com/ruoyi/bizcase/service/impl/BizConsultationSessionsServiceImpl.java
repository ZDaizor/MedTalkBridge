package com.ruoyi.bizcase.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.bizcase.domain.BizConsultationSeMessages;
import com.ruoyi.bizcase.domain.BizEvScoreDetails;
import com.ruoyi.bizcase.service.IBizCasePromptService;
import com.ruoyi.bizcase.service.IBizConsultationSeMessagesService;
import com.ruoyi.bizcase.service.IBizEvScoreDetailsService;
import com.ruoyi.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ruoyi.bizcase.mapper.BizConsultationSessionsMapper;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;

/**
 * 学生问诊列Service业务层处理
 *
 * @author daizor
 * @date 2025-09-04
 */
@Service
public class BizConsultationSessionsServiceImpl implements IBizConsultationSessionsService {
    private static final Logger logger = LoggerFactory.getLogger(BizConsultationSessionsServiceImpl.class);

    @Autowired
    private BizConsultationSessionsMapper bizConsultationSessionsMapper;

    @Autowired
    private IBizConsultationSeMessagesService bizConsultationSeMessagesService;

    @Autowired
    private IBizCasePromptService bizCasePromptService;

    @Autowired
    private IBizEvScoreDetailsService bizEvScoreDetailsService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dify.api.url}")
    private String difyApiUrl;

    /**
     * 查询学生问诊列
     *
     * @param sessionId 学生问诊列主键
     * @return 学生问诊列
     */
    @Override
    public BizConsultationSessions selectBizConsultationSessionsBySessionId(String sessionId) {
        return bizConsultationSessionsMapper.selectBizConsultationSessionsBySessionId(sessionId);
    }

    /**
     * 查询学生问诊列列表
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 学生问诊列
     */
    @Override
    public List<BizConsultationSessions> selectBizConsultationSessionsList(BizConsultationSessions bizConsultationSessions) {
        return bizConsultationSessionsMapper.selectBizConsultationSessionsList(bizConsultationSessions);
    }

    /**
     * 新增学生问诊列
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 结果
     */
    @Override
    public Long insertBizConsultationSessions(BizConsultationSessions bizConsultationSessions) {
        bizConsultationSessionsMapper.insertBizConsultationSessions(bizConsultationSessions);
        return bizConsultationSessions.getSessionId();
    }

    /**
     * 修改学生问诊列
     *
     * @param bizConsultationSessions 学生问诊列
     * @return 结果
     */
    @Override
    public int updateBizConsultationSessions(BizConsultationSessions bizConsultationSessions) {
        return bizConsultationSessionsMapper.updateBizConsultationSessions(bizConsultationSessions);
    }

    /**
     * 批量删除学生问诊列
     *
     * @param sessionIds 需要删除的学生问诊列主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSessionsBySessionIds(String[] sessionIds) {
        return bizConsultationSessionsMapper.deleteBizConsultationSessionsBySessionIds(sessionIds);
    }

    /**
     * 删除学生问诊列信息
     *
     * @param sessionId 学生问诊列主键
     * @return 结果
     */
    @Override
    public int deleteBizConsultationSessionsBySessionId(String sessionId) {
        return bizConsultationSessionsMapper.deleteBizConsultationSessionsBySessionId(sessionId);
    }

    /**
     * 调用Dify对会话记录进行评分
     * <p>
     *
     * @param sessionId 会话ID
     * @param caseId    病例ID
     * @param stepId    步骤ID
     * @return 评分结果的JSON字符串
     */
    @Override
    public String evaluateSessionWithDify(String sessionId, Long caseId, Long stepId) {
        try {
            // 1. 查询会话中的所有消息记录
//            查询改学生的地址
            BizConsultationSeMessages queryCondition = new BizConsultationSeMessages();
            queryCondition.setSessionId(sessionId);
            List<BizConsultationSeMessages> messages =
                    bizConsultationSeMessagesService.selectBizConsultationSeMessagesList(queryCondition);

            if (messages == null || messages.isEmpty()) {
                logger.warn("会话ID[{}]没有找到对话记录", sessionId);
                return createErrorResponse("没有找到对话记录");
            }

            // 2. 提取所有messageContent，按时间或顺序排列
            String conversationContent = messages.stream()
                    .sorted((m1, m2) -> {
                        try {
                            int order1 = Integer.parseInt(m1.getMessageOrder() != null ? m1.getMessageOrder() : "0");
                            int order2 = Integer.parseInt(m2.getMessageOrder() != null ? m2.getMessageOrder() : "0");
                            return Integer.compare(order1, order2);
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    })
                    .map(message -> {
                        String sender = message.getSenderType() == 1 ? "患者" : "学生";
                        return sender + ": " + message.getMessageContent();
                    })
                    .collect(Collectors.joining("\n"));

            logger.info("会话ID[{}]的对话内容整理完成，共{}*条消息", sessionId, messages.size());
            logger.debug("对话内容: \n{}", conversationContent);

            // 3. 获取Dify API Key
            String apiKey = bizCasePromptService.getPromptKeyScoreByCaseIdAndStepId(caseId, stepId);
            if (apiKey == null || apiKey.isEmpty()) {
                logger.error("未找到caseId[{}]和stepId[{}]对应的评分API Key", caseId, stepId);
                return createErrorResponse("未找到API Key");
            }

            // 4. 调用Dify进行评分
            String evaluationResult = callDifyForEvaluation(conversationContent, apiKey, sessionId);

            logger.info("会话ID[{}]的Dify评分完成", sessionId);
            return evaluationResult;

        } catch (Exception e) {
            logger.error("会话ID[{}]评分失败: {}", sessionId, e.getMessage(), e);
            return createErrorResponse("评分失败: " + e.getMessage());
        }
    }

    /**
     * 调用Dify API进行评分
     * 与DifyAiChatController类似，直接把对话内容作为query发送
     * Dify中已经配置了评分相关的prompt
     */
    private String callDifyForEvaluation(String conversationContent, String apiKey, String sessionId) {
        String url = difyApiUrl + "/chat-messages";

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("inputs", new HashMap<>());
        body.put("query", conversationContent); // 直接把对话内容作为查询
        body.put("response_mode", "blocking");
        body.put("conversation_id", ""); // 评分不需要保持会话上下文
//        todo daizor 用户消息测试注释
        body.put("user", 1); // 用户标识

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            logger.info("开始调用Dify评分API，sessionId: {}", sessionId);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            String responseBody = response.getBody();
            logger.info("Dify评分API响应状态: {}", response.getStatusCode());
            logger.debug("Dify评分原始响应: {}", responseBody);

            // 提取AI的回答
            String aiAnswer = extractAnswerFromDifyResponse(responseBody);

            if (aiAnswer != null && !aiAnswer.isEmpty()) {
                logger.info("从 Dify 获取到的评分结果: {}", aiAnswer);
                return aiAnswer;
            } else {
                logger.warn("Dify返回的响应中未找到answer字段");
                return createErrorResponse("评分响应解析失败");
            }

        } catch (Exception e) {
            logger.error("调用Dify API失败，sessionId: {}, error: {}", sessionId, e.getMessage(), e);
            return createErrorResponse("调用Dify API失败: " + e.getMessage());
        }
    }

    /**
     * 从 Dify 响应中提取 answer 字段
     */
    private String extractAnswerFromDifyResponse(String responseBody) {
        try {
            JSONObject root = JSON.parseObject(responseBody);
            if (root != null) {
                // 尝试直接获取 answer
                String answer = root.getString("answer");
                if (answer != null && !answer.isEmpty()) {
                    return answer;
                }

                // 尝试从 data 中获取
                JSONObject dataObj = root.getJSONObject("data");
                if (dataObj != null) {
                    answer = dataObj.getString("answer");
                    if (answer != null && !answer.isEmpty()) {
                        return answer;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("解析Dify响应失败: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 解析Dify评分结果并保存到评分详情表
     *
     * @param difyResult   Dify返回的评分JSON结果
     * @param evaluationId 主评价表ID
     * @return 保存成功的记录数
     */
    @Override
    public int saveDifyScoreDetails(String difyResult, Long evaluationId) {
        try {
            logger.info("开始解析Dify评分结果，evaluationId: {}", evaluationId);
            logger.debug("Dify评分原始数据: {}", difyResult);


            JSONArray scoreArray;
            try {
                scoreArray = JSON.parseArray(difyResult);
            } catch (Exception e) {
                logger.warn("解析为JSONArray失败，尝试作为单个对象处理: {}", e.getMessage());
                JSONObject singleResult = JSON.parseObject(difyResult);
                scoreArray = new JSONArray();
                scoreArray.add(singleResult);
            }

            int savedCount = 0;

            // 遍历数组中的每个评分项
            for (int i = 0; i < scoreArray.size(); i++) {
                JSONObject scoreItem = scoreArray.getJSONObject(i);

                // 创建评分详情对象
                BizEvScoreDetails scoreDetails = new BizEvScoreDetails();
                scoreDetails.setEvaluationId(evaluationId);

                // 解析字段并设置值
                try {
                    // order 对应 itemId
                    if (scoreItem.containsKey("order")) {
                        scoreDetails.setItemId(scoreItem.getInteger("order"));
                    }

                    // scored 对应 scoreAchieved (实际得分)
                    if (scoreItem.containsKey("scored")) {
                        String scoredStr = scoreItem.getString("scored");
                        if (scoredStr != null && !scoredStr.isEmpty()) {
                            scoreDetails.setScoreAchieved(new BigDecimal(scoredStr));
                        }
                    }

                    // score 对应 scoreMax (该项总分)
                    if (scoreItem.containsKey("score")) {
                        String scoreStr = scoreItem.getString("score");
                        if (scoreStr != null && !scoreStr.isEmpty()) {
                            scoreDetails.setScoreMax(new BigDecimal(scoreStr));
                        }
                    }

                    // 设置分数类型为 AI评分 (1)
                    scoreDetails.setScoreType(1);

                    // basis 对应 scoreBasis (评分依据)
                    if (scoreItem.containsKey("basis")) {
                        scoreDetails.setScoreBasis(scoreItem.getString("basis"));
                    }

                    // 保存到数据库
                    int result = bizEvScoreDetailsService.insertBizEvScoreDetails(scoreDetails);
                    if (result > 0) {
                        savedCount++;
                        logger.debug("成功保存评分详情，itemId: {}, scoreAchieved: {}, scoreMax: {}",
                                scoreDetails.getItemId(), scoreDetails.getScoreAchieved(), scoreDetails.getScoreMax());
                    } else {
                        logger.warn("保存评分详情失败，itemId: {}", scoreDetails.getItemId());
                    }

                } catch (Exception e) {
                    logger.error("处理第{}个评分项时发生错误: {}", i + 1, e.getMessage(), e);
                }
            }

            logger.info("评分详情保存完成，成功保存{}*条记录", savedCount);
            return savedCount;

        } catch (Exception e) {
            logger.error("解析和保存Dify评分结果失败: {}", e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 创建错误响应
     */
    private String createErrorResponse(String errorMessage) {
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("success", false);
        errorResponse.put("error", errorMessage);
        errorResponse.put("timestamp", System.currentTimeMillis());
        return errorResponse.toJSONString();
    }
}
