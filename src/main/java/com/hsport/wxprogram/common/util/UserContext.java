package com.hsport.wxprogram.common.util;

import com.hsport.wxprogram.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 当前登录用户相关
 */
public class UserContext {
    private static final String CURRENT_LOGIN_USER=  "loginUser";

    /**
     * 设置当前登录用户
     * @param user
     */
    public static void setUser(User user){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setAttribute(CURRENT_LOGIN_USER,user);
    }

    /**
     * 获取当前登录用户
     * @return  employee
     */
    public static User getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return (User) currentUser.getSession().getAttribute(CURRENT_LOGIN_USER);
    }
}
