package com.hsport.wxprogram;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.*;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class WxprogramApplicationTests {

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
    private RedisService redisService;


    @Test
	void contextLoads() throws Exception {
        long beginTime = System.nanoTime();
//        System.out.println(bodyService.selectBodyByUserID(2));
        //进度百分比
        Sportsplan sportsplan = sportsplanService.selectById(1);
        String planStratTime = sportsplan.getPlanStratTime();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date stratTime = simpleDateFormat.parse(String.valueOf(planStratTime));
        double days = (double) (( new Date().getTime()-stratTime.getTime() ) / (1000*3600*24));
        double planUseDays = (double)sportsplan.getPlanUseDays();
        double planper=(days/planUseDays);
        long endTime = System.nanoTime();
        System.out.println(planper);
        System.out.println("程序运行时间： "+(endTime-beginTime)+"ns");
    }

	@Test
	public void test2(){
        Coach coach = new Coach();
        System.out.println(StringUtils.isEmpty(coach.getId()));
    }

}
