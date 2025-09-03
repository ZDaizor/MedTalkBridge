package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysLogininfor;

/**
 * 系统访问记录Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysLogininforMapper 
{
    /**
     * 查询系统访问记录
     * 
     * @param infoId 系统访问记录主键
     * @return 系统访问记录
     */
    public SysLogininfor selectSysLogininforByInfoId(Long infoId);

    /**
     * 查询系统访问记录列表
     * 
     * @param sysLogininfor 系统访问记录
     * @return 系统访问记录集合
     */
    public List<SysLogininfor> selectSysLogininforList(SysLogininfor sysLogininfor);

    /**
     * 新增系统访问记录
     * 
     * @param sysLogininfor 系统访问记录
     * @return 结果
     */
    public int insertSysLogininfor(SysLogininfor sysLogininfor);

    /**
     * 修改系统访问记录
     * 
     * @param sysLogininfor 系统访问记录
     * @return 结果
     */
    public int updateSysLogininfor(SysLogininfor sysLogininfor);

    /**
     * 删除系统访问记录
     * 
     * @param infoId 系统访问记录主键
     * @return 结果
     */
    public int deleteSysLogininforByInfoId(Long infoId);

    /**
     * 批量删除系统访问记录
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysLogininforByInfoIds(Long[] infoIds);
}
