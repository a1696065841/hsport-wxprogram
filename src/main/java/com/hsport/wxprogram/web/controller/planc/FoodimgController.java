package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IFoodimgService;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.query.FoodimgQuery;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.ITodayintakeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/foodimg")
@CrossOrigin
public class FoodimgController {

    @Autowired
    public IFoodimgService foodimgService;
    @Autowired
    public ITodayintakeService todayintakeService;
    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param foodimgs  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Foodimg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody List<Foodimg> foodimgs){
        try {
            if (foodimgs.size()>0){
                List<Sportsplan> sportsplans = sportsplanService.selectMyplanOnDo(foodimgs.get(0).getUserID());
                if (sportsplans.size()==0){
                    return new AjaxResult("用户暂无计划,不能执行此操作!");
                }
            }
            for (Foodimg foodimg : foodimgs) {
                if(foodimg.getId()!=null){
                    foodimgService.updateById(foodimg);
                    String today = DateUtil.today();
            /*    Todayintake todayintake = todayintakeService.selectTheDayIntakePlanByUserID(foodimg.getUserID(), today);
                if (todayintake==null){
                    Todayintake todayintake1 = new Todayintake();
                    Todayintake lastOne = todayintakeService.getLastOne(foodimg.getUserID());
                    if (lastOne!=null){
                        todayintake1.setIntakeCalories(foodimg.getFoodCalories()+lastOne.getIntakeCalories());
                    }else {
                        todayintake1.setIntakeCalories(foodimg.getFoodCalories());
                    }
                    todayintake1.setDate(today);
                    todayintake1.setDayIntake(foodimg.getFoodCalories());
                    todayintake1.setCarbonIntake(foodimg.getFoodCarbon());
                    todayintake1.setCellulosePer(foodimg.getFoodCellulose());
                    todayintake1.setProPer(foodimg.getFoodPro());
                    todayintake1.setUserID(foodimg.getUserID());
                    todayintakeService.insert(todayintake1);
                }else {
                    todayintake.setIntakeCalories(todayintake.getIntakeCalories()+foodimg.getFoodCalories());
                    todayintake.setDayIntake(foodimg.getFoodCalories()+todayintake.getIntakeCalories());
                    todayintake.setCarbonIntake(foodimg.getFoodCarbon()+todayintake.getCarbonIntake());
                    todayintake.setCellulosePer(foodimg.getFoodCellulose()+todayintake.getCellulosePer());
                    todayintake.setProPer(foodimg.getFoodPro()+todayintake.getProPer());
                    todayintakeService.updateById(todayintake);
                }*/
                }else{
                    String today = DateUtil.today();
                    foodimg.setDate(today);
                    foodimgService.insert(foodimg);
                }
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value="新增或修改Foodimg信息")
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public AjaxResult update(@RequestBody Foodimg foodimgs){
        return null;
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Foodimg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){

        try {
            foodimgService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }



    @ApiOperation(value = "根据该用户的id查询今天上传的食物图片")
    @RequestMapping(value = "/getFoodListByUserID", method = RequestMethod.POST)
    public AjaxResult getFoodListByUserID(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Foodimg> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("date", DateUtil.today());
        userEntityWrapper.eq("userID", id);
        return AjaxResult.me().setResultObj(foodimgService.getFoodListByUserID(id));
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Foodimg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Foodimg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody FoodimgQuery query)
    {
        Page<Foodimg> page = new Page<Foodimg>(query.getPage(),query.getRows());
            page = foodimgService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Foodimg>(page.getTotal(),page.getRecords()));
    }


}
