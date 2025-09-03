package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysDictType;

/**
 * 字典类型Mapper接口
 * 
 * @author daizor
 * @date 2025-09-03
 */
public interface SysDictTypeMapper 
{
    /**
     * 查询字典类型
     * 
     * @param dictId 字典类型主键
     * @return 字典类型
     */
    public SysDictType selectSysDictTypeByDictId(Long dictId);

    /**
     * 查询字典类型列表
     * 
     * @param sysDictType 字典类型
     * @return 字典类型集合
     */
    public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType);

    /**
     * 新增字典类型
     * 
     * @param sysDictType 字典类型
     * @return 结果
     */
    public int insertSysDictType(SysDictType sysDictType);

    /**
     * 修改字典类型
     * 
     * @param sysDictType 字典类型
     * @return 结果
     */
    public int updateSysDictType(SysDictType sysDictType);

    /**
     * 删除字典类型
     * 
     * @param dictId 字典类型主键
     * @return 结果
     */
    public int deleteSysDictTypeByDictId(Long dictId);

    /**
     * 批量删除字典类型
     * 
     * @param dictIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDictTypeByDictIds(Long[] dictIds);
}
