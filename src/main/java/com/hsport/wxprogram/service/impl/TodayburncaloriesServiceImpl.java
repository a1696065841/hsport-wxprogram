package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.Todayintakeplan;
import com.hsport.wxprogram.mapper.TodayburncaloriesMapper;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
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
public class TodayburncaloriesServiceImpl extends ServiceImpl<TodayburncaloriesMapper, Todayburncalories> implements ITodayburncaloriesService {
    @Autowired
    TodayburncaloriesMapper todayburncaloriesMapper;
    @Override
    public HashMap getAvgAndAllByUserID(Long id) {
      return  todayburncaloriesMapper.getAvgAndAllByUserID(id);
    }

    @Override
    public HashMap selectSportsTimes(Long id) {
        return todayburncaloriesMapper.selectSportsTimesAvgAndAll(id);
    }
    /**
    *获取这个用户上次的运动消耗
    * */
    @Override
    public Todayburncalories getLastOne(Long id) {
        return todayburncaloriesMapper.getLastOne(id);
    }

    @Override
    public Todayburncalories selectTheDayIntakePlanByUserID(Long id, String date) {
        Todayburncalories todayburncalories = new Todayburncalories();
        todayburncalories.setUserID(id);
        todayburncalories.setDate(date);
        return  todayburncaloriesMapper.selectOne(todayburncalories);
    }
}
