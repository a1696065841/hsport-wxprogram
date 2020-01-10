package com.hsport.wxprogram.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.MD5Util;
import com.hsport.wxprogram.common.shiro.UserToken;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Lxxx;
import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.CoachGenVo;
import com.hsport.wxprogram.domain.vo.UserGenVo;
import com.hsport.wxprogram.service.*;

import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

/**
 * 登录控制器
 *
 * @author Gaojun.Zhou
 * @date 2016年12月14日 下午2:54:01
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private  IUserService userService;
    @Autowired
    private ICoachService coachService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ILxxxService lxxxService;


    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录页面
     */

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult userLogin(@RequestBody User user) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            try {
                logger.debug("手机号为" + user.getPhone() + "的小程序用户登录了");
                UserToken token = new UserToken(user.getPhone(), user.getPassword(), "User");
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名不存在或密码错误!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名不存在或密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        User myCurrentUser = (User) currentUser.getPrincipal();
        //把sessionID存在Redis里
        JSONObject loginUser = (JSONObject) JSONObject.toJSON(myCurrentUser);
        redisService.setWithTime(currentUser.getSession().getId().toString(),loginUser.toJSONString(),60*60*24*7);
        //查询并修改用户登录时间和次数
        userService.updateLoginUser(myCurrentUser);
        myCurrentUser.setPassword(null);
        UserContext.setUser(myCurrentUser); //设置当前登录用户到session以便其他地方通过UserContext.getUser
        result.put("user", myCurrentUser);
        Lxxx byUserID = lxxxService.getByUserID(myCurrentUser.getId());
        result.put("userInformation",byUserID);
        //为了做基于token会话管理,还要把sessionId返回到前台
        result.put("token", currentUser.getSession().getId());
        return AjaxResult.me().setResultObj(result);
    }

    @RequestMapping(value = "/coachLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult coachLogin(@RequestBody Coach coach) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            try {
                UserToken token = new UserToken(coach.getPhone(), coach.getPassword(), "Coach");
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        Coach myCurrentUser = (Coach) currentUser.getPrincipal();
        myCurrentUser.setPassword(null);
        CoachContext.setUser(myCurrentUser);
        //token放入缓存
        JSONObject loginUser = (JSONObject) JSONObject.toJSON(myCurrentUser);
        redisService.setWithTime(currentUser.getSession().getId().toString(),loginUser.toJSONString(),60*60*24*7);
        //给前台返回信息
        result.put("coach", myCurrentUser);
        //为了做基于token会话管理,还要把sessionId返回到前台
        result.put("token", currentUser.getSession().getId());
        return AjaxResult.me().setResultObj(result);
    }

    @RequestMapping(value = "/sysuserLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult sysuserLogin(@RequestBody Sysuser sysuser) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            try {
                UserToken token = new UserToken(sysuser.getPhone(), sysuser.getPassword(), "SystemAdmin");
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        Sysuser myCurrentUser = (Sysuser) currentUser.getPrincipal();
        myCurrentUser.setPassword(null);
        SysuserContext.setUser(myCurrentUser);
        result.put("sysuser", myCurrentUser);
        //为了做基于token会话管理,还要把sessionId返回到前台
        result.put("token", currentUser.getSession().getId());
        return AjaxResult.me().setResultObj(result);
    }

    @RequestMapping(value = "/getTestCode", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult sendTestCode(@RequestParam(value = "phone", defaultValue = "") String phoneNumber) {
        RedisTemplate<String, String> rt;
        if (phoneNumber == null || phoneNumber.length() == 0) {
            return AjaxResult.me().setSuccess(false).setMessage("手机号为空!");
        }
        String code = YanZhenCode.getCode();
        if (code == null || code.length() == 0) {
            return AjaxResult.me().setSuccess(false).setMessage("无效的验证码!");
        }
        try {
            //	SMS.batchsendsm(phoneNumber,code);
        } catch (Exception e) {
            return AjaxResult.me().setSuccess(false).setMessage("验证码发送失败!");
        }

	/*	TestCodeInforVo testCodeInfor = new TestCodeInforVo();
		testCodeInfor.setCode(code);
		testCodeInfor.setPhone(phoneNumber);
		testCodeInfor.setDate(System.currentTimeMillis());
		testCodeInforMap.put(phoneNumber,testCodeInfor);*/

        return AjaxResult.me().setSuccess(false).setMessage("验证码发送成功!");
    }


    @RequestMapping(value="/userGen",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult userGen(@RequestBody UserGenVo user){
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone",user.getPhone());
        List<User> users = userService.selectList(userEntityWrapper);
        if (users.size()>0){
           return AjaxResult.me().setSuccess(false).setMessage("手机号已存在!");
        }
        User user1 = new User();
        user1.setId(UserContext.getUUID());
        user1.setPhone(user.getPhone());
        user1.setPassword(MD5Util.creaMd5pwd(user.getPassword()));
        user1.setGenTime(DateUtil.now());
        boolean insert = userService.insert(user1);
        return AjaxResult.me().setSuccess(insert);
    }

    @RequestMapping(value="/coachGen",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult coachGen(@RequestBody CoachGenVo user){
        EntityWrapper<Coach> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone",user.getPhone());
        List<Coach> users = coachService.selectList(userEntityWrapper);
        if (YanZhenCode.isMobileNO(user.getPhone())){
            return AjaxResult.me().setSuccess(false).setMessage("手机号格式错误!");
        }else if (users.size()>0){
            return AjaxResult.me().setSuccess(false).setMessage("手机号已存在!");
        }else if (user.getPassword().length()<6){
            return AjaxResult.me().setSuccess(false).setMessage("密码太短啦!");
        }
        Coach coach = null;
        try {
            coach = new Coach();
            coach.setId(CoachContext.getUUID());
            coach.setPhone(user.getPhone());
            coach.setPassword(MD5Util.creaMd5pwd(user.getPassword()));
            coach.setGenTime(DateUtil.now());
            coach.setGymID(user.getGymID());
            coach.setCoachName(user.getCoachName());
            coach.setBirthday(user.getBirthday());
            coach.setEmploymentTime(user.getEmploymentTime());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }
        boolean insert = coachService.insert(coach);
        return AjaxResult.me().setSuccess(insert);
    }
    @RequestMapping(value="/error",method= RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public AjaxResult returnError( ){
        return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
    }

    @RequestMapping(value="/error",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult reError( ){
        return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
    }


    @RequestMapping(value="/outLogin",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult outLogin(ServletRequest request, ServletResponse response ){
        Subject subject= SecurityUtils.getSubject();
        ServletContext context= request.getServletContext();
        try {
            subject.logout();
            context.removeAttribute("error");
        }catch (SessionException e){
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(true).setMessage("用户已退出");
    }

}
