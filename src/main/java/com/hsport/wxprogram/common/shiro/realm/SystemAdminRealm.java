package com.hsport.wxprogram.common.shiro.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.shiro.MD5Util;

import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.ISysuserService;
import com.hsport.wxprogram.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemAdminRealm extends AuthorizingRealm {
    @Autowired
    private ISysuserService sysuserService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
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
        EntityWrapper<Sysuser> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("phone",name);
        Sysuser sysuser =  sysuserService.selectOne(userEntityWrapper);
        if (sysuser==null){
            throw new UnknownAccountException(name);
        }
        Object principal = sysuser;
        String password = sysuser.getPassword();
        ByteSource bytes = ByteSource.Util.bytes(MD5Util.SALT);
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, password, bytes, realmName);
        return info;
    }
}
