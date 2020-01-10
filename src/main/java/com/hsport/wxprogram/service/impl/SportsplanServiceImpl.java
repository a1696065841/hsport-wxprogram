package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.SportsplanVo;
import com.hsport.wxprogram.mapper.SportsplanMapper;
import com.hsport.wxprogram.mapper.StageplanMapper;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.service.ISportsplanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    StageplanMapper stageplanMapper;

    @Override
    public List selectPlanByUserID(Long id) {
        return sportsplanMapper.selectPlanByUserID(id);
    }

    @Override
    public   List<HashMap> selectEverDayIntakeAndBurn(SportsplanQuery sportsplanQuery) {
        return  sportsplanMapper.selectEverDayIntakeAndBurn(sportsplanQuery);
    }
    @Override
    public int selectUseDays(Sportsplan sportsplan){
        //用的天数
        String planStratTime = sportsplan.getPlanStratTime();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date stratTime = null;
        try {
            stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
      return (int) (( new Date().getTime()-stratTime.getTime() ) / (1000*3600*24));
    }

    @Override
    public Object getMyPlan(SportsplanQuery sportsplanQuery) {
        return sportsplanMapper.getMyPlan(sportsplanQuery);
    }

    @Override
    public List<Sportsplan> selectMyplanOnDo(Long userID){
        Page<Details> page = new Page<Details>(1,1);
        List<Sportsplan> sportsplans = sportsplanMapper.selectPage(page, new EntityWrapper<Sportsplan>().
                eq("userID", userID).eq("planType", 1));
        return sportsplans;
    }

    @Override
    public AjaxResult saveMyPlan(SportsplanVo sportsplanVo) {
        Sportsplan sportsplan = sportsplanVo.getSportsplan();
            if(sportsplan.getId()!=null){
                sportsplan.setPlanEndDate(null);
                sportsplanMapper.updateById(sportsplan);
                Integer sportsplanId = sportsplan.getId();
                for (Stageplan stageplan : sportsplanVo.getStageplan()) {
                    if (stageplan.getId()!=null){
                        stageplanMapper.updateById(stageplan);
                    }else {
                        stageplan.setSportsplanID(sportsplanId);
                        stageplanMapper.insert(stageplan);
                    }
                }
                return AjaxResult.me().setMessage("修改成功!");
            }else{
                Page<Details> page = new Page<Details>(1,1);
                List list = selectMyplanOnDo(sportsplan.getUserID());
                if (list.size()>0){
                    return new AjaxResult("用户已有未完成总计划,请先确认之前订单后再分配计划!!!");
                }
                sportsplan.setPlanStratTime(DateUtil.today());
                sportsplan.setPlanEndDate(null);
                sportsplan.setPlanType(1);
                sportsplanMapper.insert(sportsplan);
                Integer sportsplanId = sportsplan.getId();
                for (Stageplan stageplan : sportsplanVo.getStageplan()) {
                    stageplan.setSportsplanID(sportsplanId);
                    stageplanMapper.insert(stageplan);
                }
                return AjaxResult.me().setMessage("新增成功!");
            }
    }
}
