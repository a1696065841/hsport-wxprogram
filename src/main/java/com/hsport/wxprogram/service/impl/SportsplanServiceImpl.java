package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Body;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.mapper.SportsplanMapper;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.service.ISportsplanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-27
 */
@Service
public class SportsplanServiceImpl extends ServiceImpl<SportsplanMapper, Sportsplan> implements ISportsplanService {
    @Autowired
    SportsplanMapper sportsplanMapper;

    @Override
    public List selectPlanByUserID(Integer id) {
        return sportsplanMapper.selectPlanByUserID(id);
    }

    @Override
    public   List<HashMap> selectEverDayIntakeAndBurn(SportsplanQuery sportsplanQuery) {
        return  sportsplanMapper.selectEverDayIntakeAndBurn(sportsplanQuery);
    }

    @Override
    public HashMap getUserIntakeEverday(Integer id) {
        return sportsplanMapper.getUserIntakeEverday(id);
    }
}
