package com.hsport.wxprogram.web.controller;

import com.alibaba.fastjson.JSON;
import com.hsport.wxprogram.common.config.WxProgramPayConfig;
import com.hsport.wxprogram.common.util.wxutil.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.MD5Util;
import com.hsport.wxprogram.common.shiro.UserToken;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.CoachGenVo;
import com.hsport.wxprogram.domain.vo.UserGenVo;
import com.hsport.wxprogram.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.util.List;


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
    public ICouponUserService couponUserService;
    @Autowired
    public IUserService userService;
    @Autowired
    public ICoachService coachService;
    @Autowired
    public RedisService redisService;
    @Autowired
    public ILxxxService lxxxService;
    @Autowired
    public HttpServletRequest httpServletRequest;
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录页面
     */

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult userLogin(@RequestBody UserGenVo user) throws Exception {

        String useDsession_key = user.getSession_key();
        String code = user.getCode();
        String encryptedData = user.getEncryptedData();
        String iv = user.getIv();
        String paramss = "appid=" + WxProgramPayConfig.APPID + "&secret=" + WxProgramPayConfig.SECRET + "&js_code=" + code + "&grant_type=" + "authorization_code";
        String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", paramss);
        logger.debug("jsonStr--------------------"+jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String sessionkey = jsonObject.getString("session_key");
        // 解密
        byte[] encrypData = Base64Utils.decodeFromString(encryptedData);
        byte[] ivData = Base64Utils.decodeFromString(iv);
        byte[] sessionKey1 =null;
        if (useDsession_key==null){
            sessionKey1 = Base64Utils.decodeFromString(sessionkey);
        }else {
             sessionKey1 = Base64Utils.decodeFromString(useDsession_key);
        }
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        //java中自带的是PKCS5Padding填充，通过添加BouncyCastle组件来支持PKCS7Padding填充。
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKey1, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        String resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        JSONObject object = JSONObject.parseObject(resultString);
        // 拿到手机号码
        String phone = object.getString("phoneNumber");

        logger.debug("phoneNumber------"+phone);
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                try {
                    logger.debug("手机号为" + phone + "的小程序用户登录了");
                    UserToken token = new UserToken(phone, "123456", "User");
                    currentUser.login(token);
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                    return AjaxResult.me().setSuccess(false).setMessage("系统异常！");
                }
            }
            Map<String, Object> result = new HashMap<>();
            User myCurrentUser = (User) currentUser.getPrincipal();
            //把sessionID存在Redis里
            JSONObject loginUser = (JSONObject) JSONObject.toJSON(myCurrentUser);
            redisService.setWithTime(currentUser.getSession().getId().toString(), loginUser.toJSONString(), 60 * 60 * 24 * 7);
            //查询并修改用户登录时间和次数
            if (myCurrentUser.getLoginCount() == 0) {
                CouponUser couponUser = new CouponUser();
                couponUser.setCouponID(1);
                couponUser.setCreateTime(DateUtil.now());
                couponUser.setUserID(myCurrentUser.getId());
                couponUser.setStatus(0);
                couponUserService.insert(couponUser);
            }
            userService.updateLoginUser(myCurrentUser);
            myCurrentUser.setPassword(null);
            UserContext.setUser(myCurrentUser); //设置当前登录用户到session以便其他地方通过UserContext.getUser
            result.put("user", myCurrentUser);
            Lxxx byUserID = lxxxService.getByUserID(myCurrentUser.getId());
            result.put("userInformation", byUserID);
            //为了做基于token会话管理,还要把sessionId返回到前台
            result.put("token", currentUser.getSession().getId());
             result.put("session_key",sessionkey);
            return AjaxResult.me().setResultObj(result);
        } catch (Exception e) {
            return new AjaxResult("失败了" + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String code ="081Wu90l2d0BdC0mj41l2flVZk2Wu90Q";
        String encryptedData ="U/FIazjgoOLF008mC/uTYKi2TjiVXNeWRFQ2sIjd/HUxslvEyFoywlahBybVuUb/CsaGpBcW2y1AtsOuDsdk6gYj81PS4RbFND42BNYsTvIMHdcO0ZXQcI0jtxFbVx+mUZ8e/YCyNC6IH/HibYcQQur8Av3sotbMYGZb4pZfk8LUspWoaPZS+vqRpo6ST6h/y9fHCMxk4w6veunlP+OIWQ==";
        String iv ="YDblvrgYlULXs6ugdNfqGg==";
        String paramss = "appid=" + WxProgramPayConfig.APPID + "&secret=" + WxProgramPayConfig.SECRET + "&js_code=" + code + "&grant_type=" + "authorization_code";
        String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", paramss);
        logger.debug("jsonStr--------------------"+jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String sessionkey = jsonObject.getString("session_key");
        System.out.println(sessionkey);
        // 解密
        byte[] encrypData = Base64Utils.decodeFromString(encryptedData);
        byte[] ivData = Base64Utils.decodeFromString(iv);
        byte[] sessionKey1 = Base64Utils.decodeFromString(sessionkey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        //java中自带的是PKCS5Padding填充，通过添加BouncyCastle组件来支持PKCS7Padding填充。
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKey1, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        String resultString = new String(cipher.doFinal(encrypData), "UTF-8");

        JSONObject object = JSONObject.parseObject(resultString);
        // 拿到手机号码
        String phone = object.getString("phoneNumber");
        logger.debug("phoneNumber------"+phone);
    }

    @RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public AjaxResult wxLogin(@RequestBody UserGenVo userGenVo) {
        String code = userGenVo.getCode();
        // 被加密的数据
        String params = "appid=" + WxProgramPayConfig.APPID + "&secret=" + WxProgramPayConfig.SECRET + "&js_code=" + code + "&grant_type=" + "authorization_code";
        // 发送请求
        String wxRes = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        JSONObject jsonObject = JSONObject.parseObject(wxRes);
        UserGenVo userGenVo1 = JSONObject.toJavaObject(jsonObject, UserGenVo.class);
        String session_key = userGenVo1.getSession_key();
        //////////////// 2、将获取到的JSON格式字符串数据转换成对应的对象 ////////////////
      /*  UserLoginState userLoginState = JSONObject.toJavaObject(result, UserLoginState.class);
        userLoginStateResponse.setData(userLoginState);*/
      /*  try {
            // 参数含义：第一个，加密数据串（String）；第二个，session_key需要通过微信小程序的code获得（String）；
            // 第三个，数据加密时所使用的偏移量，解密时需要使用（String）；第四个，编码
            String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");

            if (null != result && result.length() > 0) {
                // 将解密后的JSON格式字符串转化为对象
                WXDecrypt wxDecrypt = JSON.fromJSON(result, WXDecrypt.class);

                // 获取unionId
                String str = wxDecrypt .getUnionId();

                wxDecryptResponse.setWxDecrypt(wxDecrypt);
            } else {
                wxDecryptResponse.setMsg("解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        return AjaxResult.me().setResultObj(session_key);
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
        redisService.setWithTime(currentUser.getSession().getId().toString(), loginUser.toJSONString(), 60 * 60 * 24 * 7);
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
        JSONObject loginUser = (JSONObject) JSONObject.toJSON(myCurrentUser);
        redisService.setWithTime(currentUser.getSession().getId().toString(), loginUser.toJSONString(), 60 * 60 * 24 *5);
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


    @RequestMapping(value = "/userGen", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult userGen(@RequestBody UserGenVo user) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone", user.getPhone());
        List<User> users = userService.selectList(userEntityWrapper);
        if (users.size() > 0) {
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

    @RequestMapping(value = "/coachGen", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult coachGen(@RequestBody CoachGenVo user) {
        EntityWrapper<Coach> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone", user.getPhone());
        List<Coach> users = coachService.selectList(userEntityWrapper);
        if (YanZhenCode.isMobileNO(user.getPhone())) {
            return AjaxResult.me().setSuccess(false).setMessage("手机号格式错误!");
        } else if (users.size() > 0) {
            return AjaxResult.me().setSuccess(false).setMessage("手机号已存在!");
        } else if (user.getPassword().length() < 6) {
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

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public AjaxResult returnError() {
        return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult reError() {
        return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
    }


    @RequestMapping(value = "/outLogin", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public AjaxResult outLogin(ServletRequest request, ServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        new AjaxResult().outLogin(httpServletRequest, redisService);
        ServletContext context = request.getServletContext();
        try {
            subject.logout();
            context.removeAttribute("error");
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(true).setMessage("用户已退出");
    }

}
