package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService 
{
    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 查询参数配置
     * 
     * @param configId 参数配置主键
     * @return 参数配置
     */
    @Override
    public SysConfig selectSysConfigByConfigId(Long configId)
    {
        return sysConfigMapper.selectSysConfigByConfigId(configId);
    }

    /**
     * 查询参数配置列表
     * 
     * @param sysConfig 参数配置
     * @return 参数配置
     */
    @Override
    public List<SysConfig> selectSysConfigList(SysConfig sysConfig)
    {
        return sysConfigMapper.selectSysConfigList(sysConfig);
    }

    /**
     * 新增参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    @Override
    public int insertSysConfig(SysConfig sysConfig)
    {
        sysConfig.setCreateTime(DateUtils.getNowDate());
        return sysConfigMapper.insertSysConfig(sysConfig);
    }

    /**
     * 修改参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    @Override
    public int updateSysConfig(SysConfig sysConfig)
    {
        sysConfig.setUpdateTime(DateUtils.getNowDate());
        return sysConfigMapper.updateSysConfig(sysConfig);
    }

    /**
     * 批量删除参数配置
     * 
     * @param configIds 需要删除的参数配置主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigByConfigIds(Long[] configIds)
    {
        return sysConfigMapper.deleteSysConfigByConfigIds(configIds);
    }

    /**
     * 删除参数配置信息
     * 
     * @param configId 参数配置主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigByConfigId(Long configId)
    {
        return sysConfigMapper.deleteSysConfigByConfigId(configId);
    }
}
