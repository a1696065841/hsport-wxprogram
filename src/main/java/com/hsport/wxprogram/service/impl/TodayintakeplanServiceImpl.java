package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.domain.Intaketype;
import com.hsport.wxprogram.domain.Todayintakeplan;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.hsport.wxprogram.domain.vo.IntaketypeVo;
import com.hsport.wxprogram.domain.vo.TodayIntakePlanVo;
import com.hsport.wxprogram.mapper.FoodMapper;
import com.hsport.wxprogram.mapper.IntaketypeMapper;
import com.hsport.wxprogram.mapper.TodayintakeplanMapper;
import com.hsport.wxprogram.service.ITodayintakeplanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    @Autowired
    IntaketypeMapper intaketypeMapper;
    @Autowired
    FoodMapper foodMapper;
    @Override
    public Todayintakeplan selectTheDayIntakePlanByUserID(Long id,String date) {
        List<Todayintakeplan> todayintakeplans = todayintakeplanMapper.selectList(new EntityWrapper<Todayintakeplan>().eq("userID", id).eq("date", date));
        if(todayintakeplans.size()==0){
            return null;
        }
        return  todayintakeplans.get(0);
    }
    @Override
    public AjaxResult saveTodayIntakePlanAll(TodayIntakePlanVo todayIntakePlanVo) throws Exception {
        Todayintakeplan todayintakeplan = todayIntakePlanVo.getTodayintakeplan();
        String date=DateUtil.tommrow();
        if (DateUtil.isTodayPlanTime()) {
             date=DateUtil.today();
        }else if (!DateUtil.isTodayPlanTime()){
             date=DateUtil.tommrow();
        }
        todayintakeplan.setDate(date);
        Long userID = todayintakeplan.getUserID();
       if (selectTheDayIntakePlanByUserID(userID,date)!=null){
           return new AjaxResult("用户已有今日饮食计划,请勿重复添加!");
       }
        todayintakeplanMapper.insert(todayintakeplan);
        List<IntaketypeVo> intaketypeVoList = todayIntakePlanVo.getIntaketypeVoList();
        //intaketype每餐的操作
        for (IntaketypeVo intaketypeVo : intaketypeVoList) {
            Intaketype intaketype =null;
            if (intaketypeVo.getIntaketypeID()!=null){
                //修改餐名
                intaketype = intaketypeMapper.selectById(intaketypeVo.getIntaketypeID());
                intaketype.setIntakePlanName(intaketypeVo.getIntaketypeName());
                intaketypeMapper.updateById(intaketype);
            }else {
                //新增餐名
                intaketype = new Intaketype();
                intaketype.setUserID(userID);
                intaketype.setDate(date);
                intaketype.setIntakePlanName(intaketypeVo.getIntaketypeName());
                intaketypeMapper.insert(intaketype);
            }
            //食物列表
            List<Food> foodList = intaketypeVo.getFoodList();
            Integer id = intaketype.getId();
            for (Food food : foodList) {
                if (food.getId()!=null){
                    foodMapper.updateById(food);
                }else {
                    food.setIntakeTypeID(id);
                    foodMapper.insert(food);
                }
            }
        }
        return AjaxResult.me();
    }
}
