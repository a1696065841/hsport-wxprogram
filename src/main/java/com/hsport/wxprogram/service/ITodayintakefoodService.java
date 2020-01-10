package com.hsport.wxprogram.service;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Todayintakefood;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.vo.TodayintakefoodVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2020-01-09
 */
public interface ITodayintakefoodService extends IService<Todayintakefood> {

    AjaxResult saveFoodUpdateImg(TodayintakefoodVo todayintakefoodVo);
}
