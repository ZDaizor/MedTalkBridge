package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.service.ISysRoleDeptService;

/**
 * 角色和部门关联Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysRoleDeptServiceImpl implements ISysRoleDeptService 
{
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;

    /**
     * 查询角色和部门关联
     * 
     * @param roleId 角色和部门关联主键
     * @return 角色和部门关联
     */
    @Override
    public SysRoleDept selectSysRoleDeptByRoleId(Long roleId)
    {
        return sysRoleDeptMapper.selectSysRoleDeptByRoleId(roleId);
    }

    /**
     * 查询角色和部门关联列表
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 角色和部门关联
     */
    @Override
    public List<SysRoleDept> selectSysRoleDeptList(SysRoleDept sysRoleDept)
    {
        return sysRoleDeptMapper.selectSysRoleDeptList(sysRoleDept);
    }

    /**
     * 新增角色和部门关联
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 结果
     */
    @Override
    public int insertSysRoleDept(SysRoleDept sysRoleDept)
    {
        return sysRoleDeptMapper.insertSysRoleDept(sysRoleDept);
    }

    /**
     * 修改角色和部门关联
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 结果
     */
    @Override
    public int updateSysRoleDept(SysRoleDept sysRoleDept)
    {
        return sysRoleDeptMapper.updateSysRoleDept(sysRoleDept);
    }

    /**
     * 批量删除角色和部门关联
     * 
     * @param roleIds 需要删除的角色和部门关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleDeptByRoleIds(Long[] roleIds)
    {
        return sysRoleDeptMapper.deleteSysRoleDeptByRoleIds(roleIds);
    }

    /**
     * 删除角色和部门关联信息
     * 
     * @param roleId 角色和部门关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleDeptByRoleId(Long roleId)
    {
        return sysRoleDeptMapper.deleteSysRoleDeptByRoleId(roleId);
    }
}
