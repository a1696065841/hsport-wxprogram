package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Sportsplan;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.util.AjaxResult;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
public interface ISportsplanService extends IService<Sportsplan> {
    List selectPlanByUserID(Integer id);
    List<HashMap> selectEverDayIntakeAndBurn (SportsplanQuery sportsplanQuery);
}
