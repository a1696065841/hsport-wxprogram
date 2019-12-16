package com.hsport.wxprogram.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	 
	/* @Override
     public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
         System.out.println("doCredentialsMatch  +  进来了");
         UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
 
//       Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));  
         //明文密码
         Object tokenCredentials = String.valueOf(token.getPassword());  
         Object accountCredentials = getCredentials(info);  
         //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
         return equals(tokenCredentials, accountCredentials);  
     }  */
 
     /**
      * @author Adam
      * @description 将传进来密码加密方法
      * @date 11:26 2018/9/2
      * @param
      * @return java.lang.String
      */
     private String encrypt(String data) {
         //这里可以选择自己的密码验证方式 比如 md5或者sha256等
         String sha384Hex = new Sha384Hash(data).toBase64();
         return sha384Hex;  
     }  
}