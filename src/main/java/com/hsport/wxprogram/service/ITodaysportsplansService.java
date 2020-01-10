package com.hsport.wxprogram.service;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.vo.TodaysportsplansVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface ITodaysportsplansService extends IService<Todaysportsplans> {

    AjaxResult updateSportPlans(TodaysportsplansVo todaysportsplansVo);

}
