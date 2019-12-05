package com.hsport.wxprogram.web.controller.wxduan;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.CoachQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.DateUtil;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/coach")
@CrossOrigin

public class CoachController {
    @Autowired
    public ICoachService coachService;
    @Autowired
    public IUserService userService;
    @Autowired
    public ISportsplanService sportsplanService;
    @Autowired
    public ITodayspService todayspService;
    @Autowired
    public ITodayintakeplanService todayintakeplanService;
    @Autowired
    public ISportsimgService sportsimgService;
    @Autowired
    public IFoodimgService foodimgService;



    /**
     * 保存和修改公用的
     *
     * @param coach 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Coach信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Coach coach) {
        try {
            if (coach.getId() != null) {
                coachService.updateById(coach);
            } else {
                coachService.insert(coach);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Coach信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            coachService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value = "根据url的id来获取Coach详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Coach get(@PathVariable("id") Integer id) {
        return coachService.selectById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有Coach详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Coach> list() {

        return coachService.selectList(null);
    }

    /**
     *
     *
     * 查询教练的所有客户
     *
     *
     * */
    @ApiOperation(value = "根据该教练的id查询教练的所有客户")
    @RequestMapping(value = "/getUserListByCoachID/{id}", method = RequestMethod.GET)
    public List<User> getUserListByCoachID(@PathVariable("id")Integer id) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("userID", id);
        return userService.selectList(userEntityWrapper);
    }

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Coach详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Coach详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Coach> json(@RequestBody CoachQuery query) {

        Page<Coach> page = new Page<Coach>(query.getPage(), query.getRows());
        page = coachService.selectPage(page);
        return new PageList<Coach>(page.getTotal(), page.getRecords());
    }

    @ApiOperation(value = "来获取现在的未完成工作")
    @RequestMapping(value = "/CoachWorkList/{id}", method = RequestMethod.GET)
    public Map<String, Object> CoachWorkList(@PathVariable("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        EntityWrapper<Sportsplan> sportsplanEntityWrapper = new EntityWrapper<>();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        sportsplanEntityWrapper.eq("coachID", id);
        ArrayList<Object> objects = new ArrayList<>();
        //查询到所有该教练负责的计划
        List<Sportsplan> sportsplans = sportsplanService.selectList(sportsplanEntityWrapper);
        //总计划
        for (Sportsplan sportsplan : sportsplans) {
            if (sportsplan.getMasterPlanBurn() == null || sportsplan.getMasterPlanBurn().equals("") ||
                    sportsplan.getMasterPlanIntake() == null || sportsplan.getMasterPlanIntake().equals("") ||
                    sportsplan.getPlanObjectives() == null || sportsplan.getPlanObjectives().equals("")) {
                HashMap<String, Object> sportsPlanMap = new HashMap<>();
                Integer userID = sportsplan.getUserID();
                User user = userService.selectById(userID);
                sportsPlanMap.put("username", user.getName());
                sportsPlanMap.put("userID", user.getId());
                sportsPlanMap.put("sportsplanID", sportsplan.getId());
                objects.add(sportsPlanMap);
            }
        }
        map.put("MasterPlan", objects);
        //每日运动计划 查询该教练的所有用户 查询所有用户有没有今天的计划 如果没有就加入map
        ArrayList<Object> ydList = new ArrayList<>();
        List<User> users = userService.findUserByCoachID(id);
        ArrayList<Object> mrYDList = new ArrayList<>();
        for (User user : users) {
            HashMap<String, Object> todaySpMap = new HashMap<>();
            HashMap<String, Object> tommrowSpMap = new HashMap<>();
            Todaysp todaysp = todayspService.selectTodaySpByUserID(user.getId(), DateUtil.today());
            Todaysp todaysp1 = todayspService.selectTodaySpByUserID(user.getId(), DateUtil.tommrow());
            if (todaysp == null || todaysp.getTodayBurnCalorie() == null || todaysp.getTodayBurnCaloriePlan() == null) {
                putInMap(todaySpMap, DateUtil.today(), ydList, user);
            }
            if (todaysp1 == null) {
                putInMap(tommrowSpMap, DateUtil.tommrow(), mrYDList, user);
            }
        }
        map.put("todaySP", ydList);
        map.put("tommrowSp", mrYDList);
        //每日饮食计划
        ArrayList<Object> todayYsList = new ArrayList<>();
        ArrayList<Object> mrYSList = new ArrayList<>();
        for (User user : users) {
            HashMap<String, Object> todayYsMap = new HashMap<>();
            HashMap<String, Object> tommrowYsMap = new HashMap<>();
            Todayintakeplan todayintakeplan = todayintakeplanService.selectTodayIntakePlanByUserID(user.getId(), DateUtil.today());
            Todayintakeplan tommoryPlan = todayintakeplanService.selectTodayIntakePlanByUserID(user.getId(), DateUtil.tommrow());
            if (todayintakeplan == null) {
                putInMap(todayYsMap, DateUtil.today(), todayYsList, user);
            }
            if (tommoryPlan == null) {
                putInMap(tommrowYsMap, DateUtil.today(), mrYSList, user);
            }
        }
        //图片部分 根据userID把今天的图片查出来
        List todayFoodImgList = new ArrayList<>();
        List todaySpImgList = new ArrayList<>();
        for (User user : users) {
            //map存放
            EntityWrapper<Foodimg> foodimgEntityWrapper = new EntityWrapper<>();
            foodimgEntityWrapper.eq("date", DateUtil.today());
            foodimgEntityWrapper.eq("userID", user.getId());
            List<Foodimg> foodimgs = foodimgService.selectList(foodimgEntityWrapper);
            for (Foodimg foodimg : foodimgs) {
                HashMap<String, Object> todayFoodImgMap = new HashMap<>();
                if (foodimg!= null&&(foodimg.getTodayintakeplanID()==null||foodimg.getTodayintakeplanID().equals("")
                        ||foodimg.getFoodCalories()==null||foodimg.getFoodCalories().equals("")
                        || foodimg.getFoodCarbon()==null||foodimg.getFoodCarbon().equals("")
                        ||foodimg.getFoodPro()==null||foodimg.getFoodPro().equals("")
                        ||foodimg.getFoodCalories()==null||foodimg.getFoodCalories().equals(""))){
                    System.out.println(foodimg);
                    todayFoodImgMap.put("userID",user.getId());
                    todayFoodImgMap.put("foodImg",foodimg);
                    todayFoodImgList.add(todayFoodImgMap);
                }
            }
            EntityWrapper<Sportsimg> sportsimgEntityWrapper = new EntityWrapper<>();
            sportsimgEntityWrapper.eq("date", DateUtil.today());
            sportsimgEntityWrapper.eq("userID", user.getId());
            List<Sportsimg>  sportsimgs = sportsimgService.selectList(sportsimgEntityWrapper);
            for (Sportsimg sportsimg : sportsimgs) {
                HashMap<String, Object> todaySportImgMap = new HashMap<>();
                if (sportsimg!=null&&(sportsimg.getTodayburncaloriesID()==null||sportsimg.getTodayburncaloriesID().equals("")
                ||sportsimg.getBurnCalories()==null||sportsimg.getBurnCalories().equals(""))){
                    todaySportImgMap.put("sportsImg",sportsimg);
                    todaySportImgMap.put("userID",user.getId());
                    todaySpImgList.add(todaySportImgMap);
                }
            }
        }
        map.put("todaySpImgList",todaySpImgList);
        map.put("todayFoodImgList",todayFoodImgList);
        map.put("todayIntakePlan", todayYsList);
        map.put("tommrowYsMap", mrYSList);
        return map;
    }

    //
    public static void putInMap(HashMap<String, Object> map, String date, List list, User user) {
          /*   tommrowYsMap.put("userID", user.getId());
                tommrowYsMap.put("username", user.getName());
                tommrowYsMap.put("date", DateUtil.tommrow());
                mrYSList.add(tommrowYsMap);*/
        if (date.equals(DateUtil.today())) {
            map.put("userID", user.getId());
            map.put("username", user.getName());
            map.put("date", DateUtil.today());
            list.add(map);
        }
        if (date.equals(DateUtil.tommrow())) {
            map.put("userID", user.getId());
            map.put("username", user.getName());
            map.put("date", DateUtil.tommrow());
            list.add(map);
        }
    }
}
