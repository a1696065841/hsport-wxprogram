package com.hsport.wxprogram.web.controller.wxduan;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.hsport.wxprogram.service.ITodayintakeService;
import com.hsport.wxprogram.service.ITodayintakeplanService;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
    * 保存和修改公用的
    * @param sportsplan  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Sportsplan信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sportsplan sportsplan){
        try {
            if(sportsplan.getId()!=null){
                sportsplanService.updateById(sportsplan);
            }else{
                sportsplanService.insert(sportsplan);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Sportsplan信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            sportsplanService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Sportsplan详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sportsplan get(@PathVariable("id")Integer id)
    {
        return sportsplanService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Sportsplan详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sportsplan> list(){

        return sportsplanService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Sportsplan详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sportsplan详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sportsplan> json(@RequestBody SportsplanQuery query)
    {
        Page<Sportsplan> page = new Page<Sportsplan>(query.getPage(),query.getRows());
            page = sportsplanService.selectPage(page);
            return new PageList<Sportsplan>(page.getTotal(),page.getRecords());
    }

    @ApiOperation(value="根据用户id获取对应的运动计划详细信息")
    @RequestMapping(value = "/byuserID/{id}",method = RequestMethod.GET)
    public List<Sportsplan> sportsplansByUserID(@PathVariable("id") Integer id){

        return sportsplanService.selectPlanByUserID(id);
    }

    @ApiOperation(value="获取计划的进度详情",notes = "当前进度 已过天数/总周期,累计消耗卡路里,累计摄入卡路里")
    @RequestMapping(value = "/planSchedule/{id}",method = RequestMethod.GET)
    public HashMap planSchedule(@PathVariable("id")Integer id) throws ParseException {
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer burnCalories=null;
        Integer intakeCalories=null;
        //当前计划进行日期的百分比
        Sportsplan sportsplan = sportsplanService.selectById(id);
        String planStratTime = sportsplan.getPlanStratTime();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
        double days = (double) (( new Date().getTime()-stratTime.getTime() ) / (1000*3600*24));
        double planUseDays = (double)sportsplan.getPlanUseDays();
        double planper=(days/planUseDays);
        //累计消耗卡路里
        Wrapper<Todayburncalories> burnwe = new EntityWrapper();
        burnwe.eq("sportsPlanID",sportsplan.getId());
        burnwe.orderBy("date",false);
        Todayburncalories todayburncalories = todayburncaloriesService.selectOne(burnwe);
        if (todayburncalories!=null){
             burnCalories = todayburncalories.getBurnCalories();
        }
        //累计摄入
        Wrapper<Todayintake> intakewe = new EntityWrapper();
        intakewe.eq("sportsPlanID",sportsplan.getId());
        intakewe.orderBy("date",false);
        Todayintake todayintake = todayintakeService.selectOne(intakewe);
        if (todayintake!=null){
            intakeCalories = todayintake.getIntakeCalories();
        }
        hashMap.put("planper",planper);
        hashMap.put("intakeCalories",intakeCalories);
        hashMap.put("burnCalories",burnCalories);

        return hashMap;
    }

}
