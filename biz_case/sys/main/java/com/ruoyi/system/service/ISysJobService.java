package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysJob;

/**
 * 定时任务调度Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface ISysJobService 
{
    /**
     * 查询定时任务调度
     * 
     * @param jobId 定时任务调度主键
     * @return 定时任务调度
     */
    public SysJob selectSysJobByJobId(Long jobId);

    /**
     * 查询定时任务调度列表
     * 
     * @param sysJob 定时任务调度
     * @return 定时任务调度集合
     */
    public List<SysJob> selectSysJobList(SysJob sysJob);

    /**
     * 新增定时任务调度
     * 
     * @param sysJob 定时任务调度
     * @return 结果
     */
    public int insertSysJob(SysJob sysJob);

    /**
     * 修改定时任务调度
     * 
     * @param sysJob 定时任务调度
     * @return 结果
     */
    public int updateSysJob(SysJob sysJob);

    /**
     * 批量删除定时任务调度
     * 
     * @param jobIds 需要删除的定时任务调度主键集合
     * @return 结果
     */
    public int deleteSysJobByJobIds(Long[] jobIds);

    /**
     * 删除定时任务调度信息
     * 
     * @param jobId 定时任务调度主键
     * @return 结果
     */
    public int deleteSysJobByJobId(Long jobId);
}
