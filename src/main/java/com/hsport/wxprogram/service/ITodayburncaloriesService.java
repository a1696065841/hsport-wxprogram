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
    HashMap getAvgAndAllByUserID(Long id);

    HashMap selectSportsTimes(Long id);
    Todayburncalories getLastOne(Long id);
    public Todayburncalories selectTheDayIntakePlanByUserID(Long id, String date);
}
