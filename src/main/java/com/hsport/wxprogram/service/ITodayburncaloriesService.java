package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Todayburncalories;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.Todayintakeplan;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
public interface ITodayburncaloriesService extends IService<Todayburncalories> {
    HashMap getAvgAndAllByUserID(Integer id);

    HashMap selectSportsTimes(Integer id);
    Todayburncalories getLastOne(Integer id);
    public Todayburncalories selectTheDayIntakePlanByUserID(Integer id, String date);
}
