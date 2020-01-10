package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.TodayIntakePlanVo;
import com.hsport.wxprogram.domain.vo.TodaySpVo;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.IStageplanService;
import com.hsport.wxprogram.service.ITodayspService;
import com.hsport.wxprogram.query.TodayspQuery;
import com.hsport.wxprogram.service.ITodaysportsplansService;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/todaysp")
@CrossOrigin
public class TodayspController {
    @Autowired
    public ITodayspService todayspService;
    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    public ITodaysportsplansService todaysportsplansService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    public IStageplanService stageplanService;
    /**
     * 保存和修改公用的
     *
     * @param todaysp 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Todaysp信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Todaysp todaysp) {
        try {
            if (todaysp.getId() != null) {
                todayspService.updateById(todaysp);
            } else {
                todayspService.insert(todaysp);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "新增Todaysp信息")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResult insert(@RequestBody TodaySpVo todaySpVo) {
        try {
            todayspService.saveTodaySpPlanAll(todaySpVo);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }
    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Todaysp信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            todayspService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    @ApiOperation(value = "来根据userID获取所有今日运动计划的信息")
    @RequestMapping(value = "/getByuserID", method = RequestMethod.POST)
    public AjaxResult getByuserID(@RequestBody User user) {
        Long id = user.getId();
        int usedays = 0;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<Todaysportsplans> todaysportsplans = null;
        EntityWrapper<Sportsplan> spWrapper = new EntityWrapper<>();
        spWrapper.eq("planType", 1);
        spWrapper.eq("userID", id);
        Sportsplan sportsplan = sportsplanService.selectOne(spWrapper);
        //当前计划进行日期的第几天
        if (sportsplan != null) {
            String planStratTime = sportsplan.getPlanStratTime();
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            Date stratTime = null;
            try {
                stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
                usedays = (int) ((new Date().getTime() - stratTime.getTime()) / (1000 * 3600 * 24));
            } catch (Exception e) {
                e.printStackTrace();
            }
            stringObjectHashMap.put("usedays", usedays);
            //用户的今日计划 根据对应总计划查询
            EntityWrapper<Todaysp> todayspEntityWrapper = new EntityWrapper<>();
            todayspEntityWrapper.eq("userID", user.getId());
            //日期是今天
            todayspEntityWrapper.eq("date", DateUtil.today());
            Todaysp todaysp = todayspService.selectOne(todayspEntityWrapper);
            if (todaysp != null) {
                EntityWrapper<Todaysportsplans> todaysportsplansEntityWrapper = new EntityWrapper<>();
                todaysportsplansEntityWrapper.eq("date", DateUtil.today());
                todaysportsplansEntityWrapper.eq("userID", id);
                todaysportsplans = todaysportsplansService.selectList(todaysportsplansEntityWrapper);
            }else {
                return new AjaxResult("暂无今日运动计划");
            }
            stringObjectHashMap.put("todaysp", todaysp);
            //今日运动建议
            stringObjectHashMap.put("todaysportsplans", todaysportsplans);
            //当前阶段目标
            EntityWrapper<Stageplan> stageplanEntityWrapper = new EntityWrapper<>();
            stageplanEntityWrapper.eq("sportsplanID",id);
            List<Stageplan> stageplans = stageplanService.selectList(stageplanEntityWrapper);
            String stageTarget=null;
            for (Stageplan stageplan : stageplans) {
                if (usedays>=stageplan.getStageTimeStart()&&usedays<=stageplan.getStageTimeEnd()){
                    stageTarget = stageplan.getStageTarget();
                }
            }
            stringObjectHashMap.put("stageTarget",stageTarget);
        }
        return AjaxResult.me().setResultObj(stringObjectHashMap);
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Todaysp详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Todaysp详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Todaysp> json(@RequestBody TodayspQuery query) {
        Page<Todaysp> page = new Page<Todaysp>(query.getPage(), query.getRows());
        page = todayspService.selectPage(page);
        return new PageList<Todaysp>(page.getTotal(), page.getRecords());
    }
}
