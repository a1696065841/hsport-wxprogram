package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.Todayintakeplan;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.hsport.wxprogram.mapper.SportsimgMapper;
import com.hsport.wxprogram.mapper.TodayburncaloriesMapper;
import com.hsport.wxprogram.mapper.TodaysportsplansMapper;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
@Service
public class TodayburncaloriesServiceImpl extends ServiceImpl<TodayburncaloriesMapper, Todayburncalories> implements ITodayburncaloriesService {
    @Autowired
    TodayburncaloriesMapper todayburncaloriesMapper;
    @Autowired
    TodaysportsplansMapper todaysportsplansMapper;
    @Override
    public HashMap getAvgAndAllByUserID(Long id) {
        return todayburncaloriesMapper.getAvgAndAllByUserID(id);
    }

    @Override
    public HashMap selectSportsTimes(Long id) {
        return todayburncaloriesMapper.selectSportsTimesAvgAndAll(id);
    }

    /**
     * 获取这个用户上次的运动消耗
     */
    @Override
    public Todayburncalories getLastOne(Long id) {
        return todayburncaloriesMapper.getLastOne(id);
    }

    @Override
    public Todayburncalories selectTheDayIntakePlanByUserID(Long id, String date) {
        Todayburncalories todayburncalories = new Todayburncalories();
        todayburncalories.setUserID(id);
        todayburncalories.setDate(date);
        return todayburncaloriesMapper.selectOne(todayburncalories);
    }

    @Override
    public void saveTodayburns(Todaysportsplans todaysportsplan) {
        System.out.println(todaysportsplan.getTodayBurn());
        Todayburncalories todayburncalories = selectTheDayIntakePlanByUserID(todaysportsplan.getUserID(), DateUtil.today());
        if (todayburncalories==null){
            Todayburncalories todayburncalories1 = new Todayburncalories();
            Todayburncalories lastOne = getLastOne(todaysportsplan.getUserID());
            if (lastOne!=null){
                todayburncalories1.setBurnCalories(lastOne.getBurnCalories()+todaysportsplan.getTodayBurn());
            }else {
                todayburncalories1.setBurnCalories(todaysportsplan.getTodayBurn());
            }
            todayburncalories1.setDate(DateUtil.today());
            todayburncalories1.setUserID(todaysportsplan.getUserID());
            todayburncalories1.setDayBurns(todaysportsplan.getTodayBurn());
            todayburncaloriesMapper.insert(todayburncalories1);
        }else {
            Todaysportsplans todaysportsplans1 = todaysportsplansMapper.selectById(todaysportsplan);
            System.out.println(todaysportsplans1.getTodayBurn());
            //如果已经有值则是修改卡路里参数 则需要重新确定
            if (todaysportsplans1.getTodayBurn()!=null){
                int i = todaysportsplan.getTodayBurn() - todaysportsplans1.getTodayBurn();
                System.out.println(i+"--------i");
                todayburncalories.setBurnCalories(todayburncalories.getBurnCalories()+i);
                todayburncalories.setDayBurns(todayburncalories.getDayBurns()+i);
            } else {
                todayburncalories.setBurnCalories(todayburncalories.getBurnCalories()+ todaysportsplan.getTodayBurn() );
                todayburncalories.setDayBurns(todayburncalories.getDayBurns()+todaysportsplan.getTodayBurn());
            }
            todayburncaloriesMapper.updateById(todayburncalories);
        }
    }
}
