package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.domain.Todayintakefood;
import com.hsport.wxprogram.domain.vo.TodayintakefoodVo;
import com.hsport.wxprogram.mapper.FoodimgMapper;
import com.hsport.wxprogram.mapper.TodayintakeMapper;
import com.hsport.wxprogram.mapper.TodayintakefoodMapper;
import com.hsport.wxprogram.service.ITodayintakeService;
import com.hsport.wxprogram.service.ITodayintakefoodService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2020-01-09
 */
@Service
public class TodayintakefoodServiceImpl extends ServiceImpl<TodayintakefoodMapper, Todayintakefood> implements ITodayintakefoodService {
    @Autowired
    TodayintakefoodMapper todayintakefoodMapper;
    @Autowired
    FoodimgMapper foodimgMapper;
    @Autowired
    ITodayintakeService todayintakeService;

    @Override
    public AjaxResult saveFoodUpdateImg(TodayintakefoodVo todayintakefoodVo) {
        List<Foodimg> foodimgs = todayintakefoodVo.getFoodimgs();
        String date = foodimgs.get(0).getDate();
        for (Foodimg foodimg : foodimgs) {
            foodimg.setUsed(true);
            foodimgMapper.updateById(foodimg);
        }
        List<Todayintakefood> todayintakefoods = todayintakefoodVo.getTodayintakefood();
        for (Todayintakefood todayintakefood : todayintakefoods) {
            if (todayintakefood.getId() != null) {
                //同步修改今日摄入表
                Todayintakefood todayintakefood1 = todayintakefoodMapper.selectById(todayintakefood);
                todayintakefood1.setFoodCalories(todayintakefood.getFoodCalories() - todayintakefood1.getFoodCalories());
                todayintakefood1.setFoodCarbon(todayintakefood.getFoodCarbon() - todayintakefood1.getFoodCarbon());
                todayintakefood1.setFoodCellulose(todayintakefood.getFoodCellulose() - todayintakefood1.getFoodCellulose());
                todayintakefood1.setFoodPro(todayintakefood.getFoodPro() - todayintakefood1.getFoodPro());
                todayintakefood1.setFoodFat(todayintakefood.getFoodFat() - todayintakefood1.getFoodFat());
                todayintakeService.saveUserIntake(todayintakefood1);
                todayintakefoodMapper.updateById(todayintakefood);
            } else {
                todayintakefood.setUserID(todayintakefood.getUserID());
                todayintakefood.setDate(date);
                todayintakefoodMapper.insert(todayintakefood);
                todayintakeService.saveUserIntake(todayintakefood);
            }
        }
        return null;
    }
}
