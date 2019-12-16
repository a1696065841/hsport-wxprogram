package com.hsport.wxprogram.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.UserToken;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Student;
import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IStudentService;
import com.hsport.wxprogram.service.IUserService;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    IUserService userService;
    @Autowired
    IStudentService studentService;

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登录页面
     */
    @RequestMapping
    public String login(Model model) {
        return "login";
    }


    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult userLogin(@RequestBody User user) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            try {
                logger.debug(user.getPhone()+"用户登录");
                UserToken token = new UserToken(user.getPhone(), user.getPassword(),"User");
                System.out.println(token);
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("用户名不存在!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        User myCurrentUser = (User) currentUser.getPrincipal();
        myCurrentUser.setPassword(null);
        UserContext.setUser(myCurrentUser); //设置当前登录用户到session以便其他地方通过UserContext.getUser
        result.put("user", myCurrentUser);
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
                return AjaxResult.me().setSuccess(false).setMessage("用户名不存在!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        Coach myCurrentUser = (Coach) currentUser.getPrincipal();
        myCurrentUser.setPassword(null);
        CoachContext.setUser(myCurrentUser);
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
                return AjaxResult.me().setSuccess(false).setMessage("用户名不存在!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("密码错误!");
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

    @RequestMapping(value = "/zhuce", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult login(@RequestParam("phone") String phone, @RequestParam("password") String code) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, code);
        //没有登录用户就开始验证
        if (!currentUser.isAuthenticated()) {
            //根据登录用户的手机号 寻找用户 没有就开始注册用户 注册用户的短信需要验证,而且一分钟只能发一次短信,
            EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            userEntityWrapper.eq("phone", phone);
            User user = userService.selectOne(userEntityWrapper);
            //有就执行验证  没有先执行添加用户再验证
            try {
                //UsernamePasswordToken token = new UsernamePasswordToken();
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("手机号不存在!");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("密码错误!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
            }
        }
        Map<String, Object> result = new HashMap<>();
        User user1 = (User) currentUser.getPrincipal();

        /*
        currentUser.getSession().setAttribute("loginUser",User1);
        //以下获取当前登录用户存在问题如下:
        //1 到处都散落获取当前登录用户代码
        //2 以后不用shiro所有的地方都要改变
        //解决方案:封装一个方法获取当前登录用户,以后变了只需要修改这个方法就ok了
        Subject currentUser = SecurityUtils.getSubject();
        Object loginUser = currentUser.getSession().getAttribute("loginUser");
        */
        UserContext.setUser(user1); //设置当前登录用户到session以便其他地方通过UserContext.getUser
        User user2 = UserContext.getUser();

        //除了返回登录成功与否,还要把登录的用户返回前端
        result.put("user", user2);
        //为了做基于token会话管理,还要把sessionId返回到前台
        System.out.println(currentUser.getSession().getId() + "xxxxxxxxxx");
        result.put("token", currentUser.getSession().getId());
        return AjaxResult.me().setResultObj(result);
    }

    @RequestMapping("/getTestCode")
    @ResponseBody
    public AjaxResult sendTestCode(@RequestParam(value = "phone", defaultValue = "") String phoneNumber) {
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

    /**
     * 验证码
     */
//    @RequestMapping("/captcha")
//	@ResponseBody
//    public  void captcha() throws ServletException, IOException{
//		KaptchaExtend kaptchaExtend =  new KaptchaExtend();
//		kaptchaExtend.captcha(request, resp onse);
//    }
    @RequestMapping(value = "/usertest", method = RequestMethod.GET)
    @ResponseBody
    public User studentLogin() {
        User user = UserContext.getUser();
        return user;
    }
    @RequestMapping(value = "/sysusertest", method = RequestMethod.GET)
    @ResponseBody
    public Sysuser sysuser() {
        Sysuser user = SysuserContext.getUser();
        return user;
    }
    @RequestMapping(value = "/coachtest", method = RequestMethod.GET)
    @ResponseBody
    public Coach coach3() {
        Coach user = CoachContext.getUser();
        return user;
    }
}
