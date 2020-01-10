package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Todaysp;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.hsport.wxprogram.domain.vo.TodaySpVo;
import com.hsport.wxprogram.mapper.TodayspMapper;
import com.hsport.wxprogram.mapper.TodaysportsplansMapper;
import com.hsport.wxprogram.service.ITodayspService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class TodayspServiceImpl extends ServiceImpl<TodayspMapper, Todaysp> implements ITodayspService {
    @Autowired
    TodayspMapper todayspMapper;
    @Autowired
    TodaysportsplansMapper todaysportsplansMapper;

    @Override
    public Todaysp selectTodaySpByUserID(Long id, String date) {
        Todaysp todaysp1 = new Todaysp();
        todaysp1.setUserID(id);
        todaysp1.setDate(date);
        Todaysp todaysp = todayspMapper.selectOne(todaysp1);
        return todaysp;
    }

    @Override
    public AjaxResult saveTodaySpPlanAll(TodaySpVo todaySpVo) throws Exception {
        String planTime = DateUtil.today();
        if (DateUtil.isTodayPlanTime()) {
            planTime = DateUtil.today();
        } else if (!DateUtil.isTodayPlanTime()) {
            planTime = DateUtil.tommrow();
        }
        Todaysp todaysp = todaySpVo.getTodaysp();
        Long userID = todaysp.getUserID();

        if (todaysp.getId() != null) {
            todayspMapper.updateById(todaysp);
        } else {
            todaysp.setDate(planTime);
            todayspMapper.insert(todaysp);
        }
        List<Todaysportsplans> todaysportsplans = todaySpVo.getTodaysportsplans();
        for (Todaysportsplans todaysportsplan : todaysportsplans) {
            if (todaysportsplan.getId() != null) {
                todaysportsplansMapper.updateById(todaysportsplan);
            } else {
                todaysportsplan.setTodayspId(todaysp.getId());
                todaysportsplan.setDate(planTime);
                todaysportsplan.setUserID(userID);
                todaysportsplansMapper.insert(todaysportsplan);
            }
        }
        return AjaxResult.me();
    }
}
