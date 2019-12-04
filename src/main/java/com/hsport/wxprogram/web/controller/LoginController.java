package com.hsport.wxprogram.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IUserService;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.UserContext;

import com.hsport.wxprogram.util.YanZhenCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 * @author Gaojun.Zhou
 * @date 2016年12月14日 下午2:54:01
 */
@Controller
@RequestMapping("/login")
public class LoginController{
	@Autowired
	IUserService userService;
	/**
	 * 登录页面
	 */
	@RequestMapping
	public String login(Model model){
		return "login";
	}

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public AjaxResult login(@RequestParam("phone") String phone,@RequestParam("code")String code){
		Subject currentUser = SecurityUtils.getSubject();
		//没有登录用户就开始验证
		if(!currentUser.isAuthenticated()){
			//根据登录用户的手机号 寻找用户 没有就开始注册用户 注册用户的短信需要验证,而且一分钟只能发一次短信,
            EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            userEntityWrapper.eq("phone",phone);
            User user = userService.selectOne(userEntityWrapper);
            //有就执行验证  没有先执行添加用户再验证
			try {
				UsernamePasswordToken token = new UsernamePasswordToken();
				currentUser.login(token);
			} catch (UnknownAccountException e) {
				e.printStackTrace();
				return AjaxResult.me().setSuccess(false).setMessage("手机号不存在!");
			} catch (IncorrectCredentialsException e){
				e.printStackTrace();
				return AjaxResult.me().setSuccess(false).setMessage("密码错误!");
			} catch (AuthenticationException e){
				e.printStackTrace();
				return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
			}
		}
		Map<String,Object> result = new HashMap<>();
		User user1 = (User) currentUser.getPrincipal();

        /*
        currentUser.getSession().setAttribute("loginUser",employee1);
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
		result.put("user",user2);
		//为了做基于token会话管理,还要把sessionId返回到前台
		System.out.println(currentUser.getSession().getId()+"xxxxxxxxxx");
		result.put("token",currentUser.getSession().getId());
		return AjaxResult.me().setResultObj(result);
	}

	@RequestMapping("/getTestCode")
	@ResponseBody
	public AjaxResult sendTestCode(@RequestParam(value="phone",defaultValue="") String phoneNumber ){
		if(phoneNumber == null || phoneNumber.length()==0  ){
			return AjaxResult.me().setSuccess(false).setMessage("手机号为空!");
		}
		String code = YanZhenCode.getCode();
		if( code== null || code.length()==0){
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
//		kaptchaExtend.captcha(request, response);
//    }


}
