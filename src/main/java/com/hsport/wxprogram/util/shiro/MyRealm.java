package com.hsport.wxprogram.util.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        return null;
     /*   UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String  name = token.getUsername();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("name",name);
        User user =  userService.selectOne(userEntityWrapper);
        if (user==null){
            throw new UnknownAccountException(name);
        }
        Object principal = user;
        String password = user.getPassword();
        ByteSource bytes = ByteSource.Util.bytes(MD5Util.SALT);
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, password, bytes, realmName);
        return info;*/
    }
}
