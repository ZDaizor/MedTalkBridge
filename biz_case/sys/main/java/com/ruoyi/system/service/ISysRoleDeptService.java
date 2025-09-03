package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysRoleDept;

/**
 * 角色和部门关联Service接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface ISysRoleDeptService 
{
    /**
     * 查询角色和部门关联
     * 
     * @param roleId 角色和部门关联主键
     * @return 角色和部门关联
     */
    public SysRoleDept selectSysRoleDeptByRoleId(Long roleId);

    /**
     * 查询角色和部门关联列表
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 角色和部门关联集合
     */
    public List<SysRoleDept> selectSysRoleDeptList(SysRoleDept sysRoleDept);

    /**
     * 新增角色和部门关联
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 结果
     */
    public int insertSysRoleDept(SysRoleDept sysRoleDept);

    /**
     * 修改角色和部门关联
     * 
     * @param sysRoleDept 角色和部门关联
     * @return 结果
     */
    public int updateSysRoleDept(SysRoleDept sysRoleDept);

    /**
     * 批量删除角色和部门关联
     * 
     * @param roleIds 需要删除的角色和部门关联主键集合
     * @return 结果
     */
    public int deleteSysRoleDeptByRoleIds(Long[] roleIds);

    /**
     * 删除角色和部门关联信息
     * 
     * @param roleId 角色和部门关联主键
     * @return 结果
     */
    public int deleteSysRoleDeptByRoleId(Long roleId);
}
