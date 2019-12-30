package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.Todayintake;
import com.baomidou.mybatisplus.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
public interface ITodayintakeService extends IService<Todayintake> {
    HashMap getAvgAndAllByUserID(Long id);
    public Todayintake selectTheDayIntakePlanByUserID(Long id,String date);

    Todayintake getLastOne(Long userID);
}
