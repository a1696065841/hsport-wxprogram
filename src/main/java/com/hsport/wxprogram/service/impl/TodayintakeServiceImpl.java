package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.domain.Todayintakefood;
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
    public HashMap getAvgAndAllByUserID(Long id) {
        return todayintakeMapper.getAvgAndAllByUserID(id);
    }

    @Override
    public Todayintake selectTheDayIntakePlanByUserID(Long id, String date) {
        Todayintake todayintake = new Todayintake();
        todayintake.setUserID(id);
        todayintake.setDate(date);
        return  todayintakeMapper.selectOne(todayintake);

    }

    @Override
    public void saveUserIntake(Todayintakefood todayintakefood) {
        Todayintake todayintake = selectTheDayIntakePlanByUserID(todayintakefood.getUserID(), todayintakefood.getDate());
        if (todayintake==null){
            Todayintake todayintake1 = new Todayintake();
            Todayintake lastOne = getLastOne(todayintakefood.getUserID());
            if (lastOne!=null){
                todayintake1.setIntakeCalories(todayintakefood.getFoodCalories()+lastOne.getIntakeCalories());
            }else {
                todayintake1.setIntakeCalories(todayintakefood.getFoodCalories());
            }
            //存值 没有该对象的话 就新建一个
            todayintake1.setDate(todayintakefood.getDate());
            todayintake1.setDayIntake(todayintakefood.getFoodCalories());
            todayintake1.setCarbonIntake(todayintakefood.getFoodCarbon());
            todayintake1.setCellulosePer(todayintakefood.getFoodCellulose());
            todayintake1.setProPer(todayintakefood.getFoodPro());
            todayintake1.setFatIntake(todayintakefood.getFoodFat());
            todayintake1.setUserID(todayintakefood.getUserID());
            todayintakeMapper.insert(todayintake1);
        }else {
            //改值 把变化值加上
            todayintake.setIntakeCalories(todayintake.getIntakeCalories()+todayintakefood.getFoodCalories());
            todayintake.setDayIntake(todayintakefood.getFoodCalories()+todayintake.getIntakeCalories());
            todayintake.setCarbonIntake(todayintakefood.getFoodCarbon()+todayintake.getCarbonIntake());
            todayintake.setCellulosePer(todayintakefood.getFoodCellulose()+todayintake.getCellulosePer());
            todayintake.setFatIntake(todayintakefood.getFoodFat()+todayintake.getFatIntake());
            todayintake.setProPer(todayintakefood.getFoodPro()+todayintake.getProPer());
            todayintakeMapper.updateById(todayintake);
        }
    }

    @Override
    public Todayintake getLastOne(Long userID) {
        return todayintakeMapper.getLastOne(userID);
    }
}
