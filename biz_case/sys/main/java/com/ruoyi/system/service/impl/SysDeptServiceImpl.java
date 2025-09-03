package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService 
{
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门
     * 
     * @param deptId 部门主键
     * @return 部门
     */
    @Override
    public SysDept selectSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.selectSysDeptByDeptId(deptId);
    }

    /**
     * 查询部门列表
     * 
     * @param sysDept 部门
     * @return 部门
     */
    @Override
    public List<SysDept> selectSysDeptList(SysDept sysDept)
    {
        return sysDeptMapper.selectSysDeptList(sysDept);
    }

    /**
     * 新增部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int insertSysDept(SysDept sysDept)
    {
        sysDept.setCreateTime(DateUtils.getNowDate());
        return sysDeptMapper.insertSysDept(sysDept);
    }

    /**
     * 修改部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int updateSysDept(SysDept sysDept)
    {
        sysDept.setUpdateTime(DateUtils.getNowDate());
        return sysDeptMapper.updateSysDept(sysDept);
    }

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptIds(Long[] deptIds)
    {
        return sysDeptMapper.deleteSysDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门信息
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.deleteSysDeptByDeptId(deptId);
    }
}
