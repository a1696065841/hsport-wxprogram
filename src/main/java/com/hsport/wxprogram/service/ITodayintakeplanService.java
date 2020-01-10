package com.hsport.wxprogram.service;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Todayintakeplan;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.Todaysp;
import com.hsport.wxprogram.domain.vo.TodayIntakePlanVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface ITodayintakeplanService extends IService<Todayintakeplan> {
     Todayintakeplan selectTheDayIntakePlanByUserID(Long id,String date);
     AjaxResult saveTodayIntakePlanAll(TodayIntakePlanVo todayIntakePlanVo) throws Exception;

}
