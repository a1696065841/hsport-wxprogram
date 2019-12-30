package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.ITodayintakeService;
import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.query.TodayintakeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 客户今日实际摄入
 *
 * */
@RestController
@RequestMapping("/todayintake")
@CrossOrigin

public class TodayintakeController {
    @Autowired
    public ITodayintakeService todayintakeService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ISportsplanService sportsplanService;
    /**
    * 保存和修改公用的
    * @param todayintake  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todayintake信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayintake todayintake){
        try {
            if(todayintake.getId()!=null){
                todayintakeService.updateById(todayintake);
            }else{
                todayintake.setDate(DateUtil.today());
                Long userID = todayintake.getUserID();
                Todayintake lastOne = todayintakeService.getLastOne(userID);

                if (lastOne!=null&&lastOne.getDate().equals(DateUtil.today())&&lastOne.getUserID()==todayintake.getUserID()){
                    return AjaxResult.me().setSuccess(false).setMessage("用户已经有今日饮食记录 请勿重复添加，如有修复请前往修改页面填写！");
                }
                if (lastOne!=null&&!lastOne.getDate().equals(DateUtil.today())){
                    todayintake.setIntakeCalories(todayintake.getDayIntake()+lastOne.getIntakeCalories());
                }
                Sportsplan sportsplan = sportsplanService.selectOne(new EntityWrapper<Sportsplan>().eq("userID", userID).eq("planType", 1).isNull("planEndDate"));
                if (sportsplan!=null){
                    todayintake.setSportsPlanID(sportsplan.getId());
                }else {
                    return new AjaxResult("请先填写该用户总计划");
                }
                todayintakeService.insert(todayintake);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Todayintake信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            todayintakeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }



    @ApiOperation(value="来获取用户的平均摄入和总摄入 已过天数等详细信息")
    @RequestMapping(value = "/getAvgAndAllByUserID",method = RequestMethod.POST)
    public AjaxResult getAvgAndAllByUserID(@RequestBody User user){
        return AjaxResult.me().setResultObj(todayintakeService.getAvgAndAllByUserID(user.getId()));
    }


}
