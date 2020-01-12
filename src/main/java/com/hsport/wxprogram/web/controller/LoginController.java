package com.hsport.wxprogram.web.controller;

import com.alibaba.fastjson.JSON;
import com.hsport.wxprogram.common.config.WxProgramPayConfig;
import com.hsport.wxprogram.common.util.wxutil.HttpClientUtil;
import com.hsport.wxprogram.common.util.wxutil.WechatGetUserInfoUtil;
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
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    public AjaxResult userLogin(@RequestBody UserGenVo user) {
        String code = user.getCode();
        String encryptedData = user.getEncryptedData();
        String iv = user.getIv();
        String session_key = user.getSession_key();
        // 被加密的数据
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] dataByte = decoder.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = decoder.decode(session_key);
        // 偏移量
        byte[] ivByte = decoder.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return AjaxResult.me().setResultObj(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new  AjaxResult("错误错误错误");
        }
        return AjaxResult.me().setResultObj(null);

     /* Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                try {
                    logger.debug("手机号为" + user.getPhone() + "的小程序用户登录了");
                    UserToken token = new UserToken(user.getPhone(), user.getTestcode(), "User");
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
            redisService.setWithTime(currentUser.getSession().getId().toString(), loginUser.toJSONString(), 60 * 60 * 24 * 7);
            //查询并修改用户登录时间和次数
            if (myCurrentUser.getLoginCount() == 0) {
                CouponUser couponUser = new CouponUser();
                couponUser.setCouponID(1);
                couponUser.setCreateTime(DateUtil.now());
                couponUser.setUserID(myCurrentUser.getId());
                couponUser.setStatus(0);
                CouponUser couponUser1 = new CouponUser();
                couponUser1.setCouponID(2);
                couponUser1.setCreateTime(DateUtil.now());
                couponUser1.setUserID(myCurrentUser.getId());
                couponUser1.setStatus(0);
                couponUserService.insert(couponUser);
                couponUserService.insert(couponUser1);
            }
            userService.updateLoginUser(myCurrentUser);
            myCurrentUser.setPassword(null);
            UserContext.setUser(myCurrentUser); //设置当前登录用户到session以便其他地方通过UserContext.getUser
            result.put("user", myCurrentUser);
            Lxxx byUserID = lxxxService.getByUserID(myCurrentUser.getId());
            result.put("userInformation", byUserID);
            //为了做基于token会话管理,还要把sessionId返回到前台
            result.put("token", currentUser.getSession().getId());
            return AjaxResult.me().setResultObj(result);
        }*/
    }



        @RequestMapping(value = "/coachLogin", method = RequestMethod.POST)
        @ResponseBody
        @CrossOrigin
        public AjaxResult coachLogin (@RequestBody Coach coach){
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
        public AjaxResult sysuserLogin (@RequestBody Sysuser sysuser){
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
        public AjaxResult sendTestCode (@RequestParam(value = "phone", defaultValue = "") String phoneNumber){
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
        public AjaxResult userGen (@RequestBody UserGenVo user){
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
        public AjaxResult coachGen (@RequestBody CoachGenVo user){
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
        public AjaxResult returnError () {
            return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
        }

        @RequestMapping(value = "/error", method = RequestMethod.POST)
        @CrossOrigin
        @ResponseBody
        public AjaxResult reError () {
            return AjaxResult.me().setSuccess(false).setMessage("用户未登陆或无权限");
        }


        @RequestMapping(value = "/outLogin", method = RequestMethod.POST)
        @CrossOrigin
        @ResponseBody
        public AjaxResult outLogin (ServletRequest request, ServletResponse response){
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
