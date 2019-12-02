package com.hsport.wxprogram.util;

import com.guoli.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 当前登录用户相关
 */
public class UserContext {
    private static final String CURRENT_LOGIN_USER=  "loginUser";

    /**
     * 设置当前登录用户
     * @param employee
     */
    public static void setUser(Employee employee){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setAttribute(CURRENT_LOGIN_USER,employee);
    }

    /**
     * 获取当前登录用户
     * @return  employee
     */
    public static Employee getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return (Employee) currentUser.getSession().getAttribute(CURRENT_LOGIN_USER);
    }
}
