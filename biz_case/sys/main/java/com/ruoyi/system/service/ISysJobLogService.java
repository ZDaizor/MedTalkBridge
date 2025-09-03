package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysJobLog;

/**
 * 定时任务调度日志Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface ISysJobLogService 
{
    /**
     * 查询定时任务调度日志
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 定时任务调度日志
     */
    public SysJobLog selectSysJobLogByJobLogId(Long jobLogId);

    /**
     * 查询定时任务调度日志列表
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 定时任务调度日志集合
     */
    public List<SysJobLog> selectSysJobLogList(SysJobLog sysJobLog);

    /**
     * 新增定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    public int insertSysJobLog(SysJobLog sysJobLog);

    /**
     * 修改定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    public int updateSysJobLog(SysJobLog sysJobLog);

    /**
     * 批量删除定时任务调度日志
     * 
     * @param jobLogIds 需要删除的定时任务调度日志主键集合
     * @return 结果
     */
    public int deleteSysJobLogByJobLogIds(Long[] jobLogIds);

    /**
     * 删除定时任务调度日志信息
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 结果
     */
    public int deleteSysJobLogByJobLogId(Long jobLogId);
}
