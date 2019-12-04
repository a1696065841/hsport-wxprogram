package com.hsport.wxprogram;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.Body;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IBodyService;
import com.hsport.wxprogram.service.ISportsplanService;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.hsport.wxprogram.service.IUserService;
import com.hsport.wxprogram.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        long startTime = System.nanoTime();
        Wrapper<Todayburncalories> we = new EntityWrapper();
        we.eq("sportsPlanID",1);
        we.orderBy("date",false);
        Todayburncalories todayburncalories = todayburncaloriesService.selectOne(we);
        System.out.println(todayburncalories);
        long endTime = System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
    }
  
}
