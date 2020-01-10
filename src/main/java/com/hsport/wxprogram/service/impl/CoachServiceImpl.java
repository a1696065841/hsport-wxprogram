package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.mapper.CoachMapper;
import com.hsport.wxprogram.query.CoachQuery;
import com.hsport.wxprogram.service.ICoachService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach> implements ICoachService {
    @Autowired
    CoachMapper coachMapper;
    @Override
    public List<Object> selectWithGym(CoachQuery query) {
        return coachMapper.selectWithGym(query);
    }

    @Override
    public Integer selectWithGymTotal(CoachQuery query) {
        return coachMapper.selectWithGymTotal(query);
    }
}
