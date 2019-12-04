package com.hsport.wxprogram.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Todaysp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface ITodayspService extends IService<Todaysp> {

    public Todaysp selectTodaySpByUserID(Integer id,String date);

}
