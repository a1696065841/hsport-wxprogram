package com.hsport.wxprogram;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.YanZhenCode;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.mapper.CodeMapper;
import com.hsport.wxprogram.mapper.TodayspMapper;
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
import java.util.*;

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
    @Autowired
    public ICouponUserService couponUserService;
    @Autowired
    public ICouponService couponService;
    @Autowired
    ISportsimgService sportsimgService;
    @Autowired
    public ICodeService codeService;
    @Autowired
    CodeMapper codeMapper;

    @Test
    void contextLoads() throws Exception {
    }



}
