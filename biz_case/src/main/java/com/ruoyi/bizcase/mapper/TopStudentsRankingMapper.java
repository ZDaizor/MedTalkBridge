package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.TopStudentsRanking;

/**
 * 优秀学生排行榜数据层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface TopStudentsRankingMapper {
    /**
     * 查询优秀学生排行榜
     * 
     * @return 优秀学生排行榜集合
     */
    List<TopStudentsRanking> selectTopStudentsRanking();
}