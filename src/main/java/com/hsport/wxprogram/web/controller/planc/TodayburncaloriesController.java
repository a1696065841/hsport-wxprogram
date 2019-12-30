package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.query.TodayburncaloriesQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/todayburncalories")
@CrossOrigin

public class TodayburncaloriesController {
    @Autowired
    public ITodayburncaloriesService todayburncaloriesService;
    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    HttpServletRequest request;
    /**
     * 保存和修改公用的
     *
     * @param todayburncalories 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Todayburncalories信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayburncalories todayburncalories) {
       /* AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveCoachOrSysLogin(request)){
            return new AjaxResult("用户无权限或已过期,请重新登录");
        }*/
        try {
            if (todayburncalories.getId() != null) {
                todayburncaloriesService.updateById(todayburncalories);
            } else {
                todayburncalories.setDate(DateUtil.today());
                Long userID = todayburncalories.getUserID();
                Todayburncalories lastOne = todayburncaloriesService.getLastOne(userID);
                //看看有没有今天的表填过了
                if (lastOne!=null&&lastOne.getDate().equals(DateUtil.today())&&lastOne.getUserID()==todayburncalories.getUserID()){
                    return AjaxResult.me().setSuccess(false).setMessage("用户已经有今日消耗值 请勿重复添加，如有修复请前往修改页面填写 ！");
                }                //添加总消耗值
                if (lastOne!=null&&!lastOne.getDate().equals(DateUtil.today())){
                        todayburncalories.setBurnCalories(todayburncalories.getDayBurns()+lastOne.getBurnCalories());
                }
                Sportsplan sportsplan = sportsplanService.selectOne(new EntityWrapper<Sportsplan>().eq("userID", userID).eq("planType", 1).isNull("planEndDate"));
                if (sportsplan!=null){
                    todayburncalories.setSportsPlanID(sportsplan.getId());
                }
                todayburncaloriesService.insert(todayburncalories);
            }
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
    @ApiOperation(value = "删除Todayburncalories信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
     /*   AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveCoachOrSysLogin(request)){
            return new AjaxResult("用户无权限或已过期,请重新登录");
        }*/
        try {
            todayburncaloriesService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    @ApiOperation(value="来获取用户的平均摄消耗和总消耗 已过天数等详细信息")
    @RequestMapping(value = "/getAvgAndAllByUserID",method = RequestMethod.POST)
    public AjaxResult getAvgAndAllByUserID(@RequestBody User user){

        return  AjaxResult.me().setResultObj(todayburncaloriesService.getAvgAndAllByUserID(user.getId()));
    }

    @ApiOperation(value = "根据url用户的id来获取今天Todayburncalories详细信息")
    @RequestMapping(value = "/getByUser", method = RequestMethod.POST)
    public AjaxResult getByUser(@RequestBody User user) {
        Long id = user.getId();

        EntityWrapper<Todayburncalories> todayburncaloriesEntityWrapper = new EntityWrapper<>();
        todayburncaloriesEntityWrapper.eq("userID", id);
        todayburncaloriesEntityWrapper.eq("date", DateUtil.today());
        return AjaxResult.me().setResultObj(todayburncaloriesService.selectOne(todayburncaloriesEntityWrapper));
    }

    @ApiOperation(value = "根据url用户的id来获取所有每日消耗的详细信息")
    @RequestMapping(value = "/getListByUser", method = RequestMethod.POST)
    public AjaxResult getListByUser(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Todayburncalories> todayburncaloriesEntityWrapper = new EntityWrapper<>();
        todayburncaloriesEntityWrapper.eq("userID", id);
        return AjaxResult.me().setResultObj(todayburncaloriesService.selectList(todayburncaloriesEntityWrapper));
    }



    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Todayburncalories详细信息并分页,可以按照日期来查询", notes = "根据page页数和传入的query查询条件 来获取某些Todayburncalories详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Todayburncalories> json(@RequestBody TodayburncaloriesQuery query) {
        Page<Todayburncalories> page = new Page<Todayburncalories>(query.getPage(), query.getRows());
        EntityWrapper<Todayburncalories> todayburncaloriesEntityWrapper = new EntityWrapper<>();
        if (query.getKeyword() != null) {
            todayburncaloriesEntityWrapper.eq("date", query.getKeyword());
        }
        page = todayburncaloriesService.selectPage(page,todayburncaloriesEntityWrapper);
        return new PageList<Todayburncalories>(page.getTotal(), page.getRecords());
    }
}
