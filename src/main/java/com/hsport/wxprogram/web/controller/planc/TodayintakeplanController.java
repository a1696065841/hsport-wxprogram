package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.IntaketypeVo;
import com.hsport.wxprogram.domain.vo.TodayIntakePlanVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.TodayintakeplanQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 客户今日计划摄入与饮食
 */
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
    @Autowired
    public IIntaketypeService intaketypeService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    public ITodayintakeService todayintakeService;
    /**
     * 保存和修改公用的
     *
     * @param todayintakeplan 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Todayintakeplan信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayintakeplan todayintakeplan) {
        try {
            if (todayintakeplan.getId() != null) {
                todayintakeplanService.updateById(todayintakeplan);
            } else {
                todayintakeplanService.insert(todayintakeplan);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "新增Todayintakeplan信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResult insert(@RequestBody TodayIntakePlanVo todayIntakePlanVo) {
        try {
            todayintakeplanService.saveTodayIntakePlanAll(todayIntakePlanVo);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    /**
     * 删除对象信息
     *
     * @param
     * @return
     */

    @ApiOperation(value = "删除Todayintakeplan信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Todayintakeplan todayintakeplan) {
        try {
            todayintakeplanService.deleteById(todayintakeplan);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    /**
     * 暂时用不上
     */
    @ApiOperation(value = "根据user的id来获取详细信息")
    @RequestMapping(value = "/getListByUserID", method = RequestMethod.POST)
    public AjaxResult getListByUserID(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Todayintakeplan> todayintakeplanEntityWrapper = new EntityWrapper<>();
        todayintakeplanEntityWrapper.eq("userID", id);
        return AjaxResult.me().setResultObj(todayintakeplanService.selectList(todayintakeplanEntityWrapper));
    }

    @ApiOperation(value = "来根据userID获取所有今日饮食计划的信息")
    @RequestMapping(value = "/getTodayByUserID", method = RequestMethod.POST)
    public AjaxResult getTodayByUserID(@RequestBody User user) {
        Long id = user.getId();
        Todayintakeplan todayintakeplan = null;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<Object> foodlist = new ArrayList<>();
        //用户的今日计划 根据对应总计划查询
        todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(id,DateUtil.today());
        if(todayintakeplan==null){
            return new AjaxResult("用户暂无今日饮食计划!");
        }
        stringObjectHashMap.put("todayintakeplan", todayintakeplan);
        List<Intaketype> intaketypes = intaketypeService.selectList(new EntityWrapper<Intaketype>().eq("userID", id).eq("date", DateUtil.today()));
        for (Intaketype intaketype : intaketypes) {
            HashMap<Object, Object> map = new HashMap<>();
            List<Food> intakeTypeID = foodService.selectList(new EntityWrapper<Food>().eq("intakeTypeID", intaketype.getId()));
            intaketype.getId();
            map.put("food",intakeTypeID);
            map.put("intaketype",intaketype.getIntakePlanName());
            foodlist.add(map);
        }
        stringObjectHashMap.put("todayFood", foodlist);
        Todayintake todayintake = todayintakeService.selectTheDayIntakePlanByUserID(id, DateUtil.today());
        if (todayintake!=null){
            stringObjectHashMap.put("todayIntake",todayintake.getDayIntake());
        }else {
            stringObjectHashMap.put("todayIntake",0);
        }
        return AjaxResult.me().setResultObj(stringObjectHashMap);
    }

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Todayintakeplan详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Todayintakeplan详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Todayintakeplan> json(@RequestBody TodayintakeplanQuery query) {
        Page<Todayintakeplan> page = new Page<Todayintakeplan>(query.getPage(), query.getRows());
        page = todayintakeplanService.selectPage(page);
        return new PageList<Todayintakeplan>(page.getTotal(), page.getRecords());
    }
}
