package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.SportsplanVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/sportsplan")
@CrossOrigin

public class SportsplanController {

    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    public ITodayburncaloriesService todayburncaloriesService;
    @Autowired
    public ITodayintakeService todayintakeService;
    @Autowired
    public ITodayintakeplanService todayintakeplanService;
    @Autowired
    public IStageplanService stageplanService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    RedisService redisService;

    /**
     * 保存和修改公用的
     *
     * @param sportsplanVo 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Sportsplan信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody SportsplanVo sportsplanVo) {
        AjaxResult ajaxResult = null;
        try {
            ajaxResult = sportsplanService.saveMyPlan(sportsplanVo);
        } catch (Exception e) {
            return new AjaxResult("服务器异常!请重试或联系客服");
        }
        return ajaxResult;
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Sportsplan信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        /*if (!ajaxResult.haveCoachOrSysLogin(request)){
            new AjaxResult("用户无权限或已过期,请重新登录");
        }*/
        try {
            sportsplanService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    @ApiOperation(value = "根据用户ID获取 我的计划页面信息")
    @RequestMapping(value = "/getMyPlan", method = RequestMethod.POST)
    public AjaxResult getMyPlan() {
        HashMap<String, Object> map = new HashMap<>();
        AjaxResult ajaxResult = new AjaxResult();
        User user = ajaxResult.isUserLogin(request, redisService);
        Long id = user.getId();
        if (user == null) {
            return new AjaxResult().setMessage("用户未登陆");
        }
        SportsplanQuery sportsplanQuery = new SportsplanQuery();
        sportsplanQuery.setUserID(user.getId());
        sportsplanQuery.setDate(DateUtil.today());
        Object myPlan = sportsplanService.getMyPlan(sportsplanQuery);
        List<Sportsplan> sportsplans = sportsplanService.selectMyplanOnDo(id);
        if (sportsplans.size() == 0) {
            return new AjaxResult("用户暂无计划");
        }
        int days = sportsplanService.selectUseDays(sportsplans.get(0));
        map.put("usedays",days);
        Todayintakeplan todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(id, DateUtil.today());
        if (todayintakeplan==null){
            map.put("todayintakeplan",0);
        }else {
            map.put("todayintakeplan",todayintakeplan.getIntakePlan());
        }
            map.put("myplan",myPlan);
            map.put("alldays",sportsplans.get(0).getPlanUseDays());
        return AjaxResult.me().setResultObj(map);
    }


    @ApiOperation(value = "根据用户id获取对应的运动计划详细信息")
    @RequestMapping(value = "/byuserID", method = RequestMethod.POST)
    public AjaxResult sportsplansByUserID(@RequestBody User user) {
        return AjaxResult.me().setResultObj(sportsplanService.selectPlanByUserID(user.getId()));
    }

    @ApiOperation(value = "目标与计划页面  来获取用户的平均摄入消耗和总摄入消耗 已过天数等详细信息")
    @RequestMapping(value = "/getPlanAndObjectives", method = RequestMethod.POST)
    public AjaxResult getPlanAndObjectives(@RequestBody User user) {
        Long id = user.getId();
        HashMap<String, Object> stringMapHashMap = new HashMap<>();
        List<Sportsplan> sportsplans = sportsplanService.selectMyplanOnDo(id);
        if (sportsplans.size() == 0) {
            return new AjaxResult("用户暂无计划!");
        }
        Sportsplan sportsplan = sportsplans.get(0);
        int days = sportsplanService.selectUseDays(sportsplan);
        //计划的第几天
        stringMapHashMap.put("useDays", days);
        //运动和摄入的平均值
        HashMap avgAndAllByUserID = todayburncaloriesService.getAvgAndAllByUserID(id);
        HashMap avgAndAllByUserID1 = todayintakeService.getAvgAndAllByUserID(id);
        //摄入的总卡路里和平均
        stringMapHashMap.put("IntakeAvgAndAllByUserID", avgAndAllByUserID1);
        //消耗的总值和平均
        stringMapHashMap.put("BurnAvgAndAllByUserID", avgAndAllByUserID);
        //运动时长的平均与总值
        stringMapHashMap.put("sportsTime", todayburncaloriesService.selectSportsTimes(id));
        //用户的阶段计划
        EntityWrapper<Stageplan> stageplanEntityWrapper = new EntityWrapper<>();
        stageplanEntityWrapper.eq("sportsplanID", sportsplan.getId());
        List<Stageplan> stageplans = stageplanService.selectList(stageplanEntityWrapper);
        stringMapHashMap.put("stageplans", stageplans);
        //计划目的与计划总长
        stringMapHashMap.put("sportsplan", sportsplan);
        String stageTarget = null;
        for (Stageplan stageplan : stageplans) {
            if (days >= stageplan.getStageTimeStart() && days <= stageplan.getStageTimeEnd()) {
                stageTarget = stageplan.getStageTarget();
            }
        }
        stringMapHashMap.put("stageTarget", stageTarget);
        return AjaxResult.me().setResultObj(stringMapHashMap);
    }

    @ApiOperation(value = "来获取用户每天的消耗摄入的值")
    @RequestMapping(value = "/selectEverDayIntakeAndBurn", method = RequestMethod.POST)
    public AjaxResult selectEverDayIntakeAndBurn(@RequestBody SportsplanQuery sportsplanQuery) {
        AjaxResult ajaxResult = new AjaxResult();
        User user = ajaxResult.isUserLogin(request, redisService);
        Long id = user.getId();
        if (user == null) {
            return new AjaxResult().setMessage("用户未登陆");
        }
        sportsplanQuery.setUserID(id);
        return AjaxResult.me().setResultObj(sportsplanService.selectEverDayIntakeAndBurn(sportsplanQuery));
    }

    @ApiOperation(value = "获取计划的进度详情", notes = "当前进度 已过天数/总周期,累计消耗卡路里,累计摄入卡路里")
    @RequestMapping(value = "/planSchedule", method = RequestMethod.POST)
    public AjaxResult planSchedule() throws ParseException {
        AjaxResult ajaxResult = new AjaxResult();
        User user = ajaxResult.isUserLogin(request, redisService);
        Long id = user.getId();
        if (user == null) {
            return new AjaxResult().setMessage("用户未登陆");
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer burnCalories = null;
        Integer intakeCalories = null;
        //当前计划进行日期的百分比
        Sportsplan sportsplan = sportsplanService.selectById(id);
        String planStratTime = sportsplan.getPlanStratTime();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
        double days = (double) ((new Date().getTime() - stratTime.getTime()) / (1000 * 3600 * 24));
        double planUseDays = (double) sportsplan.getPlanUseDays();
        double planper = (days / planUseDays);
        //累计消耗卡路里
        Wrapper<Todayburncalories> burnwe = new EntityWrapper();
        burnwe.eq("sportsPlanID", sportsplan.getId());
        burnwe.orderBy("date", false);
        Todayburncalories todayburncalories = todayburncaloriesService.selectOne(burnwe);
        if (todayburncalories != null) {
            burnCalories = todayburncalories.getBurnCalories();
        }
        //累计摄入
        Wrapper<Todayintake> intakewe = new EntityWrapper();
        intakewe.eq("sportsPlanID", sportsplan.getId());
        intakewe.orderBy("date", false);
        Todayintake todayintake = todayintakeService.selectOne(intakewe);
        if (todayintake != null) {
            intakeCalories = todayintake.getIntakeCalories();
        }
        hashMap.put("planper", planper);
        hashMap.put("intakeCalories", intakeCalories);
        hashMap.put("burnCalories", burnCalories);
        return AjaxResult.me().setResultObj(hashMap);
    }

}
