package com.hsport.wxprogram.common.shiro.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.MD5Util;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.UserContext;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User activeUser = (User) SecurityUtils.getSubject().getPrincipal();
        return null;
    }


    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String  name = token.getUsername();
        Object principal=null;
        String password=null;
        //寻找手机对应用户
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone",name);
        User user =  userService.selectOne(userEntityWrapper);
        if (user==null){
            User user1 = new User();
            user1.setId(UserContext.getUUID());
            user1.setGenTime(DateUtil.now());
            user1.setPhone(name);
            user1.setLoginCount(0);
            user1.setPassword(MD5Util.creaMd5pwd("123456"));
            boolean insert = userService.insert(user1);
             principal = user1;
             password = user1.getPassword();
        }else {
            principal = user;
            password = user.getPassword();
        }
        ByteSource bytes = ByteSource.Util.bytes(MD5Util.SALT);
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, password,null, realmName);
        return info;
    }
}
