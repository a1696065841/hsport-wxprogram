package com.hsport.wxprogram.common.util;

import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 当前登录用户相关
 */
public class SysuserContext {
    private static final String CURRENT_LOGIN_USER=  "loginUser";

    /**
     * 设置当前登录用户
     * @param sysuser
     */
    public static void setUser(Sysuser sysuser){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setAttribute(CURRENT_LOGIN_USER,sysuser);
    }

    /**
     * 获取当前登录用户
     * @return  employee
     */
    public static Sysuser getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return (Sysuser) currentUser.getSession().getAttribute(CURRENT_LOGIN_USER);
    }
}
