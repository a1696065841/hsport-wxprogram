package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.domain.Todayintakeplan;
import com.hsport.wxprogram.mapper.TodayintakeMapper;
import com.hsport.wxprogram.service.ITodayintakeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
@Service
public class TodayintakeServiceImpl extends ServiceImpl<TodayintakeMapper, Todayintake> implements ITodayintakeService {
    @Autowired
    TodayintakeMapper todayintakeMapper;

    @Override
    public HashMap getAvgAndAllByUserID(Integer id) {
        return todayintakeMapper.getAvgAndAllByUserID(id);
    }

    @Override
    public Todayintake selectTheDayIntakePlanByUserID(Integer id, String date) {
        Todayintake todayintake = new Todayintake();
        todayintake.setUserID(id);
        todayintake.setDate(date);
        return  todayintakeMapper.selectOne(todayintake);

    }
}
