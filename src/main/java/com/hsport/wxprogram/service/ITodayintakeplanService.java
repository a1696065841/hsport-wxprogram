package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Todayintakeplan;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.Todaysp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface ITodayintakeplanService extends IService<Todayintakeplan> {
    public Todayintakeplan selectTodayIntakePlanByUserID(Integer id,String date);
}
