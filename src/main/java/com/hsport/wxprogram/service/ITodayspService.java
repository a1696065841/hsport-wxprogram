package com.hsport.wxprogram.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Todaysp;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.vo.TodaySpVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface ITodayspService extends IService<Todaysp> {

    public Todaysp selectTodaySpByUserID(Long id,String date);

    AjaxResult saveTodaySpPlanAll(TodaySpVo todayspVo) throws Exception;
}
