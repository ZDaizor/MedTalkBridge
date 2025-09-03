package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysConfig;

/**
 * 参数配置Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysConfigMapper 
{
    /**
     * 查询参数配置
     * 
     * @param configId 参数配置主键
     * @return 参数配置
     */
    public SysConfig selectSysConfigByConfigId(Long configId);

    /**
     * 查询参数配置列表
     * 
     * @param sysConfig 参数配置
     * @return 参数配置集合
     */
    public List<SysConfig> selectSysConfigList(SysConfig sysConfig);

    /**
     * 新增参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    public int insertSysConfig(SysConfig sysConfig);

    /**
     * 修改参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    public int updateSysConfig(SysConfig sysConfig);

    /**
     * 删除参数配置
     * 
     * @param configId 参数配置主键
     * @return 结果
     */
    public int deleteSysConfigByConfigId(Long configId);

    /**
     * 批量删除参数配置
     * 
     * @param configIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysConfigByConfigIds(Long[] configIds);
}
