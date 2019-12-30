package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Todayintakeplan;
import com.hsport.wxprogram.mapper.TodayintakeplanMapper;
import com.hsport.wxprogram.service.ITodayintakeplanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class TodayintakeplanServiceImpl extends ServiceImpl<TodayintakeplanMapper, Todayintakeplan> implements ITodayintakeplanService {
    @Autowired
    TodayintakeplanMapper todayintakeplanMapper;

    @Override
    public Todayintakeplan selectTheDayIntakePlanByUserID(Long id,String date) {
        Todayintakeplan todayintakeplan = new Todayintakeplan();
        todayintakeplan.setUserID(id);
        todayintakeplan.setDate(date);
        return  todayintakeplanMapper.selectOne(todayintakeplan);
    }
}
