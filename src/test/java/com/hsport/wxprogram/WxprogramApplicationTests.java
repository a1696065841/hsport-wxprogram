package com.hsport.wxprogram;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.YanZhenCode;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.query.GymQuery;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class WxprogramApplicationTests {
    @Autowired
    public IProductService productService;
    @Autowired
    public RedisService redisService;
    @Autowired
    public  IProductserviceService productserviceService;
    @Autowired
    public HttpServletRequest request;
    @Autowired
    public  IProductGymService productGymService;
    @Autowired
    public IDetailsService detailsService;
    @Autowired
    public IGymService gymService;
    @Autowired
    IBodyService bodyService;
    @Autowired
    IUserService userService;
    @Autowired
    ISportsplanService sportsplanService;
    @Autowired
    public ITodayburncaloriesService todayburncaloriesService;
    @Autowired
    JedisPool jedisPool;
    @Autowired
    IRegionService regionService;
    @Autowired
    IOrderService orderService;
    @Test
    void contextLoads() throws Exception {
        long beginTime = System.nanoTime();
//        System.out.println(bodyService.selectBodyByUserID(2));
        //进度百分比
        Sportsplan sportsplan = sportsplanService.selectById(1);
        String planStratTime = sportsplan.getPlanStratTime();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
        double days = (double) ((new Date().getTime() - stratTime.getTime()) / (1000 * 3600 * 24));
        double planUseDays = (double) sportsplan.getPlanUseDays();
        double planper = (days / planUseDays);
        long endTime = System.nanoTime();
        System.out.println(planper);
        System.out.println("程序运行时间： " + (endTime - beginTime) + "ns");
    }

    @Test
    public void test2() {
        Product product = new Product();
        product.setId(1);
        HashMap<String, Object> map = new HashMap<>();
        Product product1 = productService.selectById(product);
        map.put("product",product);
        Integer id = product1.getId();
        List<Details> details = detailsService.selectList(new EntityWrapper<Details>().eq("productID", id));
        map.put("details",details);
        List<Productservice> productservices = productserviceService.selectList(new EntityWrapper<Productservice>().eq("productID", id));
        map.put("productservices",productservices);
        List<ProductGym> productGyms = productGymService.selectList(new EntityWrapper<ProductGym>().eq("productID", id));
        List<Gym> gyms=new ArrayList<>();
        for (ProductGym productGym : productGyms) {
            Gym gym = gymService.selectById(productGym.getGymID());
            gyms.add(gym);
        }
        map.put("gyms",gyms);
        System.out.println(map);
    }

}
