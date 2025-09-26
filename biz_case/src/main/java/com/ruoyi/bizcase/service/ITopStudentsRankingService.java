package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.TopStudentsRanking;

/**
 * 优秀学生排行榜服务层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ITopStudentsRankingService {
    /**
     * 查询优秀学生排行榜
     * 
     * @return 优秀学生排行榜集合
     */
    List<TopStudentsRanking> selectTopStudentsRanking();
}