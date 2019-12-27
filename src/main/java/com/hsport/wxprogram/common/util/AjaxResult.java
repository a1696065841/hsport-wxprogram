package com.hsport.wxprogram.common.util;

import com.alibaba.fastjson.JSONObject;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.RedisService;
import com.hsport.wxprogram.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

//Ajax请求响应对象的类
public class AjaxResult {
    private boolean success = true;
    private String message = "操作成功!";
    @Autowired
    RedisService redisService;
    //返回到前台对象
    private Object resultObj;


    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }

    //AjaxResult.me()成功
    //AjaxResult.me().setMessage()成功
    //AjaxResult.me().setSuccess(false),setMessage("失败");
    public static AjaxResult me() {
        return new AjaxResult();
    }

    //成功
    public AjaxResult() {
    }

    //失败并且有提示
    public AjaxResult(String message) {
        this.success = false;
        this.message = message;
    }

    public  User isUserLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        User o = null;
        if (token != null) {
            String s = redisService.get(token);
            if (s != null) {
                try {
                    o = JSONObject.parseObject(s, User.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return o;
    }

    public Coach isCoachLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Coach o = null;
        if (token != null) {
            String s = redisService.get(token);
            if (s != null) {
                try {
                    o = JSONObject.parseObject(s, Coach.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return o;
    }

    public  Sysuser isSysUserLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Sysuser o = null;
        if (token != null) {
            String s = redisService.get(token);
            if (s != null) {
                try {
                    o = JSONObject.parseObject(s, Sysuser.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return o;
    }
    public   boolean  haveCoachOrSysLogin(HttpServletRequest request){
        Sysuser sysUserLogin =isSysUserLogin(request);
        Coach coachLogin = isCoachLogin(request);
        if (sysUserLogin==null&&coachLogin==null){
            return false;
        }
        return true;
    }
    public boolean  haveCoachLogin(HttpServletRequest request){
        Coach coachLogin = isCoachLogin(request);
        if (coachLogin==null){
            return false;
        }
        return true;
    }

    public  boolean  haveUserLogin(HttpServletRequest request){
        User user = isUserLogin(request);
        if (user==null){
            return false;
        }
        return true;
    }

    public   boolean  haveSysUserLogin(HttpServletRequest request){
        Sysuser sysuser = isSysUserLogin(request);
        if (sysuser==null){
            return false;
        }
        return true;
    }

    public boolean  haveUserOrSysLogin(HttpServletRequest request){
        Sysuser sysUserLogin =isSysUserLogin(request);
        User user = isUserLogin(request);
        if (sysUserLogin==null&&user==null){
            return false;
        }
        return true;
    }

    public boolean  haveUserOrCoachLogin(HttpServletRequest request){
        Coach coachLogin = isCoachLogin(request);
        User user = isUserLogin(request);
        if (coachLogin==null&&user==null){
            return false;
        }
        return true;
    }
    public boolean  haveAnyOneLogin(HttpServletRequest request){
        Coach coachLogin = isCoachLogin(request);
        Sysuser sysuser = isSysUserLogin(request);
        User user = isUserLogin(request);
        if (coachLogin==null&&user==null&&sysuser==null){
            return false;
        }
        return true;
    }
}