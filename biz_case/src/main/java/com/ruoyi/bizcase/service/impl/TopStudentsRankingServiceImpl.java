package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.TopStudentsRanking;
import com.ruoyi.bizcase.mapper.TopStudentsRankingMapper;
import com.ruoyi.bizcase.service.ITopStudentsRankingService;

/**
 * 优秀学生排行榜服务层实现
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class TopStudentsRankingServiceImpl implements ITopStudentsRankingService {
    
    @Autowired
    private TopStudentsRankingMapper topStudentsRankingMapper;

    /**
     * 查询优秀学生排行榜
     * 
     * @return 优秀学生排行榜集合
     */
    @Override
    public List<TopStudentsRanking> selectTopStudentsRanking() {
        return topStudentsRankingMapper.selectTopStudentsRanking();
    }
}