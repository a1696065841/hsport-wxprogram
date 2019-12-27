package com.hsport.wxprogram.common.util;

import com.hsport.wxprogram.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.UUID;

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
    public static Integer getUUID() {
        String id =null;
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();

        //去掉随机ID的短横线
        id = id.replace("-", "");
        System.out.println(id);
        //将随机ID换成数字
        int num = id.hashCode();
        //取绝对值
        num = num < 0 ? -num : num;

        id = String.valueOf(num);

        return num;
    }

}
