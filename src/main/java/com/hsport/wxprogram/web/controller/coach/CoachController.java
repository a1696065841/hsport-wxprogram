package com.hsport.wxprogram.web.controller.coach;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.CoachContext;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.WorkListVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.CoachQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @Autowired
    public IOrderService orderService;
    @Autowired
    public IProductService productService;
    @Autowired
    public IProductserviceService productserviceService;
    @Autowired
    public ITodaysportsplansService todaysportsplansService;
    @Autowired
    public ILxxxService lxxxService;

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
     * 查询教练的所有客户
     */
    @ApiOperation(value = "根据该教练的id查询教练的所有客户")
    @RequestMapping(value = "/getUserListByCoachID", method = RequestMethod.GET)
    public List getUserListByCoachID() {
        List<Object> list = new ArrayList<>();
        Coach coach = CoachContext.getUser();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("coachID", coach.getId());
        List<User> users = userService.selectList(userEntityWrapper);
        for (User user : users) {
            HashMap<String, Object> map = new HashMap<>();
            Lxxx byUserID = lxxxService.getByUserID(user.getId());
            map.put("userLxxx",byUserID);
            map.put("userPhone",user.getPhone());
            list.add(map);
        }
        return list;
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

    @ApiOperation(value = "根据教练id来获取现在的未完成工作按员工区分")
    @RequestMapping(value = "/CoachWorkListByUser", method = RequestMethod.GET)
    public List CoachWorkListByUser() throws Exception {
        /** vo 类
         * 用户名
         * 用户id
         * 教练未完成工作（用户未完成订单）：{
         *       work1：{
         *           Oder对应的产品和规格
         *       }
         *       work2：{
         *           Oder对应的产品和规格以及产品的类型
         *
         *       }
         * }
         * //判断有没有产品类型为2的服务类型   2类型才会有计划这种概念
         * 用户总计划：{
         *
         * }
         * 订单对应未填写的大计划 没写就给教练未完成工作里面加就好
         * 订单大计划对应的今日计划
         * 用户今日饮食计划
         * 用户上传的图片
         * */
        Coach coach = CoachContext.getUser();
        List todayFoodImgList = new ArrayList<>();
        List todaySpImgList = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<>();
        List<User> users = userService.findUserByCoachID(coach.getId());
        //遍历用户
        for (User user : users) {
            HashMap<String, Object> map = new HashMap<>();
            List<HashMap> workList = new ArrayList<>();
            Lxxx byUserID = lxxxService.getByUserID(user.getId());
            if (byUserID==null){
                map.put("username","用户暂未命名");
            }
            map.put("username", byUserID.getName());
            map.put("userID", user.getId());
            //查到用户的未完成订单
            EntityWrapper<Order> orderEntityWrapper = new EntityWrapper<>();
            System.out.println("-----------------------------------group");
            List<Order> orders = orderService.selectList(orderEntityWrapper.eq("userID", user.getId()).andNew().eq("orderType", 0).groupBy("productID"));
            Iterator<Order> iterator = orders.iterator();
            ArrayList<Object> serviceList = new ArrayList<>();
            //遍历订单
            while (iterator.hasNext()) {
                //查到订单对应的产品
                Order order = iterator.next();
                HashMap<String, Object> workMap = new HashMap<>();
                Product product = productService.selectById(order.getProductID());
                //找到对应产品的计划服务项
                List<Productservice> productservices = productserviceService.selectList(new EntityWrapper<Productservice>().eq("productID", product.getId()));
                Iterator<Productservice> productserviceIterator = productservices.iterator();
                while (productserviceIterator.hasNext()) {
                    WorkListVo workListVo = new WorkListVo();
                    Productservice productservice = productserviceIterator.next();
                    Todaysportsplans todaysportsplans=null;
                    Todayintakeplan todayintakeplan =null;
                    //如过是长期计划类型的产品
                    if (product.getProductType() == 2) {
                        //遍历查询计划 如果现在是凌晨6点钱就查有没有今天的  超过了今天凌晨6点就查有没有明天的
                        if (DateUtil.DateCompare(DateUtil.now(),DateUtil.todaySix(),"yyyy.MM.dd HH:mm")==-1){
                             todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                    .eq("date",DateUtil.today()).eq("productServiceID", productservice.getId()));
                        }else if (DateUtil.DateCompare(DateUtil.now(),DateUtil.todaySix(),"yyyy.MM.dd HH:mm")==1){
                             todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                    .eq("date",DateUtil.tommrow()).eq("productServiceID", productservice.getId()));
                        }
                        //如果没有这个计划的话  那就加进该服务的工作列表里
                        if (todaysportsplans == null) {
                            System.out.println("todaysportsplans == null");
                            setWorkList(serviceList,workListVo,product,productservice);
                        }
                        //如果他有饮食计划类型的服务查该用户今天有没有饮食计划没有就加上
                    }else if (product.getProductType()==3){
                        if (DateUtil.DateCompare(DateUtil.now(),DateUtil.todaySix(),"yyyy.MM.dd HH:mm")==-1){
                             todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
                        }
                        else if (DateUtil.DateCompare(DateUtil.now(),DateUtil.todaySix(),"yyyy.MM.dd HH:mm")==1){
                             todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.tommrow());
                        }
                        if (todayintakeplan==null){
                            System.out.println("饮食计划 == null");
                            setWorkList(serviceList,workListVo,product,productservice);
                        }
                    }
                    else  if (product.getProductType()==1){
                        setWorkList(serviceList,workListVo,product,productservice);
                        /*workListVo.setProductName(product.getProductName());
                        workListVo.setProductserviceName(productservice.getName());
                        serviceList.add(workListVo);*/
                    }
                }
                workList.add(workMap);
            }
            //map存放
            EntityWrapper<Foodimg> foodimgEntityWrapper = new EntityWrapper<>();
            foodimgEntityWrapper.eq("date", DateUtil.today());
            foodimgEntityWrapper.eq("userID", user.getId());
            List<Foodimg> foodimgs = foodimgService.selectList(foodimgEntityWrapper);
            for (Foodimg foodimg : foodimgs) {
                HashMap<String, Object> todayFoodImgMap = new HashMap<>();
                if (foodimg != null && (StringUtils.isEmpty(foodimg.getTodayintakeplanID())
                        || StringUtils.isEmpty(foodimg.getFoodCalories())
                        || StringUtils.isEmpty(foodimg.getFoodCarbon())
                        || StringUtils.isEmpty(foodimg.getFoodPro())
                        || StringUtils.isEmpty(foodimg.getFoodCalories()))) {
                    todayFoodImgMap.put("foodImg", foodimg);
                    todayFoodImgList.add(todayFoodImgMap);
                }
            }
            EntityWrapper<Sportsimg> sportsimgEntityWrapper = new EntityWrapper<>();
            sportsimgEntityWrapper.eq("date", DateUtil.today());
            sportsimgEntityWrapper.eq("userID", user.getId());
            List<Sportsimg> sportsimgs = sportsimgService.selectList(sportsimgEntityWrapper);
            for (Sportsimg sportsimg : sportsimgs) {
                HashMap<String, Object> todaySportImgMap = new HashMap<>();
                if (sportsimg != null && (sportsimg.getTodayburncaloriesID() == null || sportsimg.getTodayburncaloriesID().equals("")
                        || sportsimg.getBurnCalories() == null || sportsimg.getBurnCalories().equals(""))) {
                    Productservice productservice = productserviceService.selectById(sportsimg.getProductserviceID());
                    if (productservice!=null){
                        todaySportImgMap.put("productserviceName", productservice.getName());
                        todaySportImgMap.put("productservice", productservice.getId());
                    }
                    todaySportImgMap.put("productName",productService.selectById(productservice.getProductID()).getProductName());
                    todaySportImgMap.put("sportsImg", sportsimg);
                    todaySpImgList.add(todaySportImgMap);
                }
            }
            map.put("workList", serviceList);
            map.put("todaySpImgList", todaySpImgList);
            map.put("todayFoodImgList", todayFoodImgList);
            list.add(map);
        }
        return list;
    }
    void setWorkList(List list,WorkListVo workListVo,Product product,Productservice productservice){
        workListVo.setProductName(product.getProductName());
        workListVo.setProductserviceName(productservice.getName());
        list.add(workListVo);
    }

  /*  @ApiOperation(value = "根据教练id来获取现在的未完成工作按工作类型区分的")
    @RequestMapping(value = "/CoachWorkList/{id}", method = RequestMethod.GET)
    public Map<String, Object> CoachWorkList(@PathVariable("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        EntityWrapper<Sportsplan> sportsplanEntityWrapper = new EntityWrapper<>();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        sportsplanEntityWrapper.eq("coachID", id);
        ArrayList<Object> objects = new ArrayList<>();
        //图片部分 根据userID把今天的图片查出来
        List todayFoodImgList = new ArrayList<>();
        List todaySpImgList = new ArrayList<>();
        //查询到所有该教练负责的计划
        List<Sportsplan> sportsplans = sportsplanService.selectList(sportsplanEntityWrapper);
        //总计划
        for (Sportsplan sportsplan : sportsplans) {
            if (sportsplan.getMasterPlanBurn() == null || sportsplan.getMasterPlanBurn().equals("") ||
                    sportsplan.getMasterPlanIntake() == null || sportsplan.getMasterPlanIntake().equals("") ||
                    sportsplan.getPlanObjectives() == null || sportsplan.getPlanObjectives().equals("")) {
                HashMap<String, Object> sportsPlanMap = new HashMap<>();
                Integer userID = sportsplan.getUserID();
                Lxxx byUserID = lxxxService.getByUserID(userID);
                User user = userService.selectById(userID);
                sportsPlanMap.put("username", byUserID.getName());
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
        map.put("todaySP", ydList);
        map.put("tommrowSp", mrYDList);
        //每日饮食计划
        ArrayList<Object> todayYsList = new ArrayList<>();
        ArrayList<Object> mrYSList = new ArrayList<>();
        for (User user : users) {
            //map存放
            EntityWrapper<Foodimg> foodimgEntityWrapper = new EntityWrapper<>();
            foodimgEntityWrapper.eq("date", DateUtil.today());
            foodimgEntityWrapper.eq("userID", user.getId());
            List<Foodimg> foodimgs = foodimgService.selectList(foodimgEntityWrapper);
            for (Foodimg foodimg : foodimgs) {
                HashMap<String, Object> todayFoodImgMap = new HashMap<>();
                if (foodimg != null && (StringUtils.isEmpty(foodimg.getTodayintakeplanID())
                        || StringUtils.isEmpty(foodimg.getFoodCalories())
                        || StringUtils.isEmpty(foodimg.getFoodCarbon())
                        || StringUtils.isEmpty(foodimg.getFoodPro())
                        || StringUtils.isEmpty(foodimg.getFoodCalories()))) {
                    System.out.println(foodimg);
                    todayFoodImgMap.put("userID", user.getId());
                    todayFoodImgMap.put("foodImg", foodimg);
                    todayFoodImgList.add(todayFoodImgMap);
                }
            }
            EntityWrapper<Sportsimg> sportsimgEntityWrapper = new EntityWrapper<>();
            sportsimgEntityWrapper.eq("date", DateUtil.today());
            sportsimgEntityWrapper.eq("userID", user.getId());
            List<Sportsimg> sportsimgs = sportsimgService.selectList(sportsimgEntityWrapper);
            for (Sportsimg sportsimg : sportsimgs) {
                HashMap<String, Object> todaySportImgMap = new HashMap<>();
                if (sportsimg != null && (sportsimg.getTodayburncaloriesID() == null || sportsimg.getTodayburncaloriesID().equals("")
                        || sportsimg.getBurnCalories() == null || sportsimg.getBurnCalories().equals(""))) {
                    todaySportImgMap.put("sportsImg", sportsimg);
                    todaySportImgMap.put("userID", user.getId());
                    todaySpImgList.add(todaySportImgMap);
                }
            }
            HashMap<String, Object> todaySpMap = new HashMap<>();
            HashMap<String, Object> tommrowSpMap = new HashMap<>();
            Todaysp todaysp = todayspService.selectTodaySpByUserID(user.getId(), DateUtil.today());
            Todaysp todaysp1 = todayspService.selectTodaySpByUserID(user.getId(), DateUtil.tommrow());
            if (todaysp == null  || todaysp.getTodayBurnCaloriePlan() == null) {
                putInMap(todaySpMap, DateUtil.today(), ydList, user);
            }
            if (todaysp1 == null) {
                putInMap(tommrowSpMap, DateUtil.tommrow(), mrYDList, user);
            }
            //饮食map和
            HashMap<String, Object> todayYsMap = new HashMap<>();
            HashMap<String, Object> tommrowYsMap = new HashMap<>();
            Todayintakeplan todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
            Todayintakeplan tommoryPlan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.tommrow());
            if (todayintakeplan == null) {
                putInMap(todayYsMap, DateUtil.today(), todayYsList, user);
            }
            if (tommoryPlan == null) {
                putInMap(tommrowYsMap, DateUtil.today(), mrYSList, user);
            }
        }
        map.put("todaySpImgList", todaySpImgList);
        map.put("todayFoodImgList", todayFoodImgList);
        map.put("todayIntakePlan", todayYsList);
        map.put("tommrowYsMap", mrYSList);
        return map;
    }

      void putInMap(HashMap<String, Object> map, String date, List list, User user) {
          *//*   tommrowYsMap.put("userID", user.getId());
                tommrowYsMap.put("username", user.getName());
                tommrowYsMap.put("date", DateUtil.tommrow());
                mrYSList.add(tommrowYsMap);*//*
        Lxxx byUserID = lxxxService.getByUserID(user.getId());
        if (date.equals(DateUtil.today())) {
            map.put("userID", user.getId());
            map.put("username", byUserID.getName());
            map.put("date", DateUtil.today());
            list.add(map);
        }
        if (date.equals(DateUtil.tommrow())) {
            map.put("userID", user.getId());
            map.put("username", byUserID.getName());
            map.put("date", DateUtil.tommrow());
            list.add(map);
        }
    }*/
}
