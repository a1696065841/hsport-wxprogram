package com.hsport.wxprogram.web.controller.coach;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.MD5Util;
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
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ITodayintakeService todayintakeService;
    @Autowired
    public ITodayburncaloriesService todayburncaloriesService;
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
    @Autowired
    public RedisService redisService;
    @Autowired
    HttpServletRequest request;

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
                coach.setPassword(MD5Util.creaMd5pwd(coach.getPassword()));
                coachService.updateById(coach);
            } else {
                coach.setId(CoachContext.getUUID());
                coach.setPassword(MD5Util.creaMd5pwd(coach.getPassword()));
                coachService.insert(coach);
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
    @ApiOperation(value = "删除Coach信息", notes = "删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Coach id) {
        AjaxResult ajaxResult = new AjaxResult();
        Sysuser sysUserLogin = ajaxResult.isSysUserLogin(request, redisService);
        if (sysUserLogin == null) {
            return new AjaxResult("用户已过期，请重新登录");
        }
        try {
            coachService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value = "根据url的id来获取Coach详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult get(@RequestBody Coach coach) {
        Long id = coach.getId();
        return AjaxResult.me().setResultObj(coachService.selectById(id));
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有Coach详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list() {
        AjaxResult ajaxResult = new AjaxResult();
        Sysuser sysUserLogin = ajaxResult.isSysUserLogin(request, redisService);
        if (sysUserLogin == null) {
            return new AjaxResult("用户已过期，请重新登录");
        }
        return AjaxResult.me().setResultObj(coachService.selectList(null));
    }

    /**
     * 查询教练的所有客户
     */
    @ApiOperation(value = "根据该教练的id查询教练的所有客户")
    @RequestMapping(value = "/getUserListByCoachID", method = RequestMethod.POST)
    public AjaxResult getUserListByCoachID(@RequestBody Coach coach) {
        List<Object> list = new ArrayList<>();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("coachID", coach.getId());
        List<User> users = userService.selectList(userEntityWrapper);
        if (users.size() > 0) {
            for (User user : users) {
                HashMap<String, Object> map = new HashMap<>();
                Lxxx byUserID = lxxxService.getByUserID(user.getId());
                if (byUserID != null) {
                    map.put("userLxxx", byUserID);
                    map.put("userPhone", user.getPhone());
                } else {
                    map.put("userLxxx", "未填写");
                    map.put("userPhone", "未填写");
                }
                list.add(map);
            }
        }
        return AjaxResult.me().setResultObj(list);
    }

    /**
     * 分页查询数据
     * 213346621
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Coach详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Coach详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public AjaxResult json(@RequestBody CoachQuery query) {
        Integer page = (query.getPage() - 1) * query.getRows();
        query.setPage(page);
        HashMap<String, Object> map = new HashMap<>();
        List<Object> objects = coachService.selectWithGym(query);
        map.put("coachs", objects);
        map.put("total", coachService.selectWithGymTotal(query));
        return AjaxResult.me().setResultObj(map);
    }

    @ApiOperation(value = "根据教练id来获取现在的未完成工作按员工区分")
    @RequestMapping(value = "/CoachWorkListByUser", method = RequestMethod.POST)
    public AjaxResult CoachWorkListByUser() throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        Coach coach = ajaxResult.isCoachLogin(request, redisService);
        if (coach == null) {
            return new AjaxResult("用户已过期，请重新登录");
        }
        List todayFoodImgList = new ArrayList<>();
        List todaySpImgList = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<>();
        List<User> users = userService.findUserByCoachID(coach.getId());
        //遍历用户
        for (User user : users) {
            HashMap<String, Object> map = new HashMap<>();
            List<HashMap> workList = new ArrayList<>();
            Lxxx byUserID = lxxxService.getByUserID(user.getId());
            //查到用户的未完成订单
            EntityWrapper<Order> orderEntityWrapper = new EntityWrapper<>();
            List<Order> orders = orderService.selectList(orderEntityWrapper.eq("userID", user.getId()).andNew().eq("orderType", 0).groupBy("productID"));
            if (orders.size() > 0) {
                map.put("userID", user.getId());
                map.put("loginPhone", user.getPhone());
                if (byUserID!=null){
                    map.put("username", byUserID.getName());
                    map.put("telPhone",byUserID.getTelphone());
                    map.put("sex",byUserID.getSex());
                    map.put("WeiXin",byUserID.getWeixinNum());
                }else {
                    map.put("username", "");
                    map.put("telPhone","");
                    map.put("sex","");
                    map.put("WeiXin","");
                }
            }
            for (Order order : orders) {
                //查到订单对应的产品
                Product product = productService.selectById(order.getProductID());
                HashMap<String, Object> productMap = new HashMap<>();
                ArrayList<WorkListVo> undoWorkList = new ArrayList<>();
                productMap.put("product", product);
                //总计划
                WorkListVo theMostSportPlanWork = new WorkListVo();
                theMostSportPlanWork.setName("总计划");
                theMostSportPlanWork.setServiceType(0);
                Sportsplan sportsplan = sportsplanService.selectOne(new EntityWrapper<Sportsplan>().eq("userID", user.getId()).eq("planType",1));
                if (product.getProductType() == 2 && sportsplan == null) {
                    theMostSportPlanWork.setOnWork(true);
                } else {
                    theMostSportPlanWork.setOnWork(false);
                }
                undoWorkList.add(theMostSportPlanWork);
                //找到对应产品的计划服务项
                List<Productservice> productservices = productserviceService.selectList(new EntityWrapper<Productservice>().eq("productID", product.getId()));
                for (Productservice productservice : productservices) {
                    //专业
                    Todaysportsplans todaysportsplans = null;
                    Todayintakeplan todayintakeplan = null;
                    Todayintake todayintake = null;
                    Todayburncalories todayburncalories = null;
                    //普通一次性结清服务
                    if (productservice.getServiceType() == 1) {
                        WorkListVo workListVo = new WorkListVo(productservice.getName(), productservice.getServiceType(), true);
                        undoWorkList.add(workListVo);
                        //运动计划  或者自定义计划
                    } else if (productservice.getServiceType() == 2) {
                        if (DateUtil.isTodayPlanTime()) {
                            todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                    .eq("date", DateUtil.today()));
                        } else if (!DateUtil.isTodayPlanTime()) {
                            todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                    .eq("date", DateUtil.tommrow()));
                        }
                        ifNullPutInWorkVo(todaysportsplans, undoWorkList, productservice);
                    }
                    //饮食计划
                    else if (productservice.getServiceType() == 3) {
                        if (DateUtil.DateCompare(DateUtil.now(), DateUtil.todaySix(), "yyyy.MM.dd HH:mm") == -1) {
                            todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
                        } else if (DateUtil.DateCompare(DateUtil.now(), DateUtil.todaySix(), "yyyy.MM.dd HH:mm") == 1) {
                            todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.tommrow());
                        }
                        ifNullPutInWorkVo(todayintakeplan, undoWorkList, productservice);
                        //饮食分析
                    } else if (productservice.getServiceType() == 4) {
                        WorkListVo workListVo = new WorkListVo(productservice.getName(), productservice.getServiceType(), isSportsImg(user, productservice));
                        undoWorkList.add(workListVo);
                        //运动分析
                    } else if (productservice.getServiceType() == 5) {
                        WorkListVo workListVo = new WorkListVo(productservice.getName(), productservice.getServiceType(), isfoodsImg(user, productservice));
                        undoWorkList.add(workListVo);
                    }
                }
                productMap.put("undoWorkList", undoWorkList);
                workList.add(productMap);
            }
            map.put("workList", workList);
            list.add(map);
        }
        return AjaxResult.me().setResultObj(list);
    }


    void ifNullPutInWorkVo(Object obj, List undoWorkList, Productservice productservice) {
        System.out.println(obj + "obj------");
        if (obj == null) {
            WorkListVo workListVo = new WorkListVo(productservice.getName(), productservice.getServiceType(), true);
            undoWorkList.add(workListVo);
        } else {
            WorkListVo workListVo = new WorkListVo(productservice.getName(), productservice.getServiceType(), false);
            undoWorkList.add(workListVo);
        }
    }

    boolean isSportsImg(User user, Productservice productservice) {
        EntityWrapper<Sportsimg> sportsimgEntityWrapper = new EntityWrapper<>();
        sportsimgEntityWrapper.eq("date", DateUtil.today());
        sportsimgEntityWrapper.eq("userID", user.getId());
        sportsimgEntityWrapper.eq("isUsed",0);
        List<Sportsimg> sportsimgs = sportsimgService.selectList(sportsimgEntityWrapper);
        for (Sportsimg sportsimg : sportsimgs) {
            HashMap<String, Object> todaySportImgMap = new HashMap<>();
            if (sportsimg != null && (sportsimg.getBurnCalories() == null || sportsimg.getBurnCalories().equals(""))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    boolean isfoodsImg(User user, Productservice productservice) {
        EntityWrapper<Foodimg> foodimgEntityWrapper = new EntityWrapper<>();
        foodimgEntityWrapper.eq("date", DateUtil.today());
        foodimgEntityWrapper.eq("userID", user.getId());
        foodimgEntityWrapper.eq("isUsed",0);
        List<Foodimg> foodimgs = foodimgService.selectList(foodimgEntityWrapper);
        for (Foodimg foodimg : foodimgs) {
            HashMap<String, Object> todayFoodImgMap = new HashMap<>();
            if (foodimg != null && (StringUtils.isEmpty(foodimg.getFoodCalories())
                    || StringUtils.isEmpty(foodimg.getFoodCarbon())
                    || StringUtils.isEmpty(foodimg.getFoodPro())
                    || StringUtils.isEmpty(foodimg.getFoodCalories()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //运动图片
         /*
                Productservice productservice1 = productserviceService.selectById(sportsimg.getProductserviceID());
                if (productservice != null) {
                        todaySportImgMap.put("productserviceName", productservice.getName());
                        todaySportImgMap.put("productservice", productservice.getId());
                    }
                    todaySportImgMap.put("productName", productService.selectById(productservice.getProductID()).getProductName());
                    todaySportImgMap.put("sportsImg", sportsimg);
                    todaySpImgList.add(todaySportImgMap);*/

/*      WorkListVo workListVo = new WorkListVo();
                Todaysportsplans todaysportsplans = null;
                Todayintakeplan todayintakeplan = null;
                Todayintake todayintake = null;
                Todayburncalories todayburncalories = null;
                //如过是长期计划类型的产品
                if (productservice.getServiceType() == 2) {
                    //遍历查询计划 如果现在是凌晨6点钱就查有没有今天的  超过了今天凌晨6点就查有没有明天的
                    if (DateUtil.isTodayPlanTime()) {
                        todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                .eq("date", DateUtil.today()).eq("productServiceID", productservice.getId()));
                    } else if (!DateUtil.isTodayPlanTime()) {
                        todaysportsplans = todaysportsplansService.selectOne(new EntityWrapper<Todaysportsplans>().eq("userID", user.getId())
                                .eq("date", DateUtil.tommrow()).eq("productServiceID", productservice.getId()));
                    }
                    //如果没有这个计划的话  那就加进该服务的工作列表里
                    if (todaysportsplans == null) {
                        System.out.println("todaysportsplans == null");
                        setWorkList(serviceList, workListVo, product, productservice);
                    }
                    //如果他有饮食计划类型的服务查该用户今天有没有饮食计划没有就加上
                    *//**
     * 改成判断产品有没有3类型的服务就好 一个个遍历会多传几次名字
     * *//*
                } else if (productHaveThree(product)) {
                    if (DateUtil.DateCompare(DateUtil.now(), DateUtil.todaySix(), "yyyy.MM.dd HH:mm") == -1) {
                        todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
                        todayintake = todayintakeService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
                        todayburncalories = todayburncaloriesService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.today());
                    } else if (DateUtil.DateCompare(DateUtil.now(), DateUtil.todaySix(), "yyyy.MM.dd HH:mm") == 1) {
                        todayintakeplan = todayintakeplanService.selectTheDayIntakePlanByUserID(user.getId(), DateUtil.tommrow());
                    }
                    ifNullPutInWorkList(todayintakeplan, serviceList, workListVo, product, productservice);
                    ifNullPutInWorkList(todayintake, serviceList, workListVo, product, productservice);
                    ifNullPutInWorkList(todayburncalories, serviceList, workListVo, product, productservice);
                } else if (productservice.getServiceType() == 1) {
                    setWorkList(serviceList, workListVo, product, productservice);
                        *//*workListVo.setProductName(product.getProductName());
                        workListVo.setProductserviceName(productservice.getName());
                        serviceList.add(workListVo);*//*
                }*/

}
