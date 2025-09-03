package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.service.ISysDictTypeService;

/**
 * 字典类型Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService 
{
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    /**
     * 查询字典类型
     * 
     * @param dictId 字典类型主键
     * @return 字典类型
     */
    @Override
    public SysDictType selectSysDictTypeByDictId(Long dictId)
    {
        return sysDictTypeMapper.selectSysDictTypeByDictId(dictId);
    }

    /**
     * 查询字典类型列表
     * 
     * @param sysDictType 字典类型
     * @return 字典类型
     */
    @Override
    public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType)
    {
        return sysDictTypeMapper.selectSysDictTypeList(sysDictType);
    }

    /**
     * 新增字典类型
     * 
     * @param sysDictType 字典类型
     * @return 结果
     */
    @Override
    public int insertSysDictType(SysDictType sysDictType)
    {
        sysDictType.setCreateTime(DateUtils.getNowDate());
        return sysDictTypeMapper.insertSysDictType(sysDictType);
    }

    /**
     * 修改字典类型
     * 
     * @param sysDictType 字典类型
     * @return 结果
     */
    @Override
    public int updateSysDictType(SysDictType sysDictType)
    {
        sysDictType.setUpdateTime(DateUtils.getNowDate());
        return sysDictTypeMapper.updateSysDictType(sysDictType);
    }

    /**
     * 批量删除字典类型
     * 
     * @param dictIds 需要删除的字典类型主键
     * @return 结果
     */
    @Override
    public int deleteSysDictTypeByDictIds(Long[] dictIds)
    {
        return sysDictTypeMapper.deleteSysDictTypeByDictIds(dictIds);
    }

    /**
     * 删除字典类型信息
     * 
     * @param dictId 字典类型主键
     * @return 结果
     */
    @Override
    public int deleteSysDictTypeByDictId(Long dictId)
    {
        return sysDictTypeMapper.deleteSysDictTypeByDictId(dictId);
    }
}
