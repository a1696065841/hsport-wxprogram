package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Todaysp;
import com.hsport.wxprogram.mapper.TodayspMapper;
import com.hsport.wxprogram.service.ITodayspService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class TodayspServiceImpl extends ServiceImpl<TodayspMapper, Todaysp> implements ITodayspService {
    @Autowired
    TodayspMapper todayspMapper;

    @Override
    public Todaysp selectTodaySpByUserID(Integer id,String date) {
        Todaysp todaysp1 = new Todaysp();
        todaysp1.setUserID(id);
        todaysp1.setDate(date);
        Todaysp todaysp = todayspMapper.selectOne(todaysp1);
        return todaysp;
    }
}
