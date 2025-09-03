package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;

/**
 * 字典数据Service业务层处理
 * 
 * @author daizor
 * @date 2025-09-03
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService 
{
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询字典数据
     * 
     * @param dictCode 字典数据主键
     * @return 字典数据
     */
    @Override
    public SysDictData selectSysDictDataByDictCode(Long dictCode)
    {
        return sysDictDataMapper.selectSysDictDataByDictCode(dictCode);
    }

    /**
     * 查询字典数据列表
     * 
     * @param sysDictData 字典数据
     * @return 字典数据
     */
    @Override
    public List<SysDictData> selectSysDictDataList(SysDictData sysDictData)
    {
        return sysDictDataMapper.selectSysDictDataList(sysDictData);
    }

    /**
     * 新增字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public int insertSysDictData(SysDictData sysDictData)
    {
        sysDictData.setCreateTime(DateUtils.getNowDate());
        return sysDictDataMapper.insertSysDictData(sysDictData);
    }

    /**
     * 修改字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public int updateSysDictData(SysDictData sysDictData)
    {
        sysDictData.setUpdateTime(DateUtils.getNowDate());
        return sysDictDataMapper.updateSysDictData(sysDictData);
    }

    /**
     * 批量删除字典数据
     * 
     * @param dictCodes 需要删除的字典数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDictDataByDictCodes(Long[] dictCodes)
    {
        return sysDictDataMapper.deleteSysDictDataByDictCodes(dictCodes);
    }

    /**
     * 删除字典数据信息
     * 
     * @param dictCode 字典数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDictDataByDictCode(Long dictCode)
    {
        return sysDictDataMapper.deleteSysDictDataByDictCode(dictCode);
    }
}
