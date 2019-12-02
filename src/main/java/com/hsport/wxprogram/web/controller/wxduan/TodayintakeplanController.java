package com.hsport.wxprogram.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.service.IFoodService;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.ITodayintakeplanService;
import com.hsport.wxprogram.query.TodayintakeplanQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 客户今日计划摄入与饮食
 * */
@RestController
@RequestMapping("/todayintakeplan")
@CrossOrigin

public class TodayintakeplanController {
    @Autowired
    public ITodayintakeplanService todayintakeplanService;
    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    public IFoodService foodService;
    /**
    * 保存和修改公用的
    * @param todayintakeplan  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todayintakeplan信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayintakeplan todayintakeplan){
        try {
            if(todayintakeplan.getId()!=null){
                todayintakeplanService.updateById(todayintakeplan);
            }else{
                todayintakeplanService.insert(todayintakeplan);
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
    @ApiOperation(value="删除Todayintakeplan信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            todayintakeplanService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Todayintakeplan详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Todayintakeplan get(@PathVariable("id")Integer id) {
        return todayintakeplanService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Todayintakeplan详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Todayintakeplan> list(){

        return todayintakeplanService.selectList(null);
    }

    @ApiOperation(value="来根据userID获取所有今日饮食计划的信息")
    @RequestMapping(value = "/getByuserID/{id}",method = RequestMethod.GET)
    public HashMap<String, Object> getByuserID(@PathVariable("id")Integer id){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Todayintakeplan todayintakeplan=null;
        List<Food> todaysportsplans=null;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        //查询用户正在进行的计划
        EntityWrapper<Sportsplan> spWrapper = new EntityWrapper<>();
        spWrapper.eq("planType",1);
        spWrapper.eq("userID",1);
        Sportsplan sportsplan = sportsplanService.selectOne(spWrapper);
        //当前计划进行日期的第几天
        if(sportsplan!=null){
            //用户的今日计划 根据对应总计划查询
            EntityWrapper<Todayintakeplan> todayintakeplanEntityWrapper = new EntityWrapper<>();
            todayintakeplanEntityWrapper.eq("sportsPlanID",sportsplan.getId());
            //日期是今天
            todayintakeplanEntityWrapper.eq("date",simpleDateFormat.format(new Date()));
            todayintakeplan = todayintakeplanService.selectOne(todayintakeplanEntityWrapper);
        }
        stringObjectHashMap.put("todayintakeplan",todayintakeplan);
        //今日运动建议
        if (todayintakeplan!=null){
            EntityWrapper<Food> foodEntityWrapper = new EntityWrapper<>();
            foodEntityWrapper.eq("todayIntakePlanID",todayintakeplan.getId());
            todaysportsplans = foodService.selectList(foodEntityWrapper);
        }
        stringObjectHashMap.put("todaysportsplans",todaysportsplans);
        return stringObjectHashMap;
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Todayintakeplan详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Todayintakeplan详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Todayintakeplan> json(@RequestBody TodayintakeplanQuery query)
    {
        Page<Todayintakeplan> page = new Page<Todayintakeplan>(query.getPage(),query.getRows());
            page = todayintakeplanService.selectPage(page);
            return new PageList<Todayintakeplan>(page.getTotal(),page.getRecords());
    }
}
