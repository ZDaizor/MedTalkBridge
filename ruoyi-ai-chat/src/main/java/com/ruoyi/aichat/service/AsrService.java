package com.ruoyi.aichat.service;


import com.ruoyi.aichat.dto.AsrRequest;
import com.ruoyi.aichat.dto.QueryResponse;

public interface AsrService {
    /**
     * 提交语音识别任务
     *
     * @param request 识别请求参数
     * @return 任务ID
     */
    String submitTask(AsrRequest request);

    /**
     * 查询识别结果
     *
     * @param taskId 任务ID
     * @return 识别结果
     */

    QueryResponse queryResult(String taskId);

    /**
     * 提交并等待结果（同步方式）
     *
     * @param request  识别请求参数
     * @param maxRetry 最大重试次数
     * @param interval 查询间隔（毫秒）
     * @return 识别结果
     */
    QueryResponse submitAndWait(AsrRequest request, int maxRetry, long interval);

    /**
     * 极速版：单次请求直接返回结果（flash接口）
     * @param request  请求（audio.url 或 audio.data 二选一）
     * @return 识别结果
     */
    QueryResponse flashRecognize(AsrRequest request);
}