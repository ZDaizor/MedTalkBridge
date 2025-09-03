package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOperLogMapper;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 操作日志记录Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService 
{
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    /**
     * 查询操作日志记录
     * 
     * @param operId 操作日志记录主键
     * @return 操作日志记录
     */
    @Override
    public SysOperLog selectSysOperLogByOperId(Long operId)
    {
        return sysOperLogMapper.selectSysOperLogByOperId(operId);
    }

    /**
     * 查询操作日志记录列表
     * 
     * @param sysOperLog 操作日志记录
     * @return 操作日志记录
     */
    @Override
    public List<SysOperLog> selectSysOperLogList(SysOperLog sysOperLog)
    {
        return sysOperLogMapper.selectSysOperLogList(sysOperLog);
    }

    /**
     * 新增操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    @Override
    public int insertSysOperLog(SysOperLog sysOperLog)
    {
        return sysOperLogMapper.insertSysOperLog(sysOperLog);
    }

    /**
     * 修改操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    @Override
    public int updateSysOperLog(SysOperLog sysOperLog)
    {
        return sysOperLogMapper.updateSysOperLog(sysOperLog);
    }

    /**
     * 批量删除操作日志记录
     * 
     * @param operIds 需要删除的操作日志记录主键
     * @return 结果
     */
    @Override
    public int deleteSysOperLogByOperIds(Long[] operIds)
    {
        return sysOperLogMapper.deleteSysOperLogByOperIds(operIds);
    }

    /**
     * 删除操作日志记录信息
     * 
     * @param operId 操作日志记录主键
     * @return 结果
     */
    @Override
    public int deleteSysOperLogByOperId(Long operId)
    {
        return sysOperLogMapper.deleteSysOperLogByOperId(operId);
    }
}
