package com.hsport.wxprogram.util.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
    public static final String SALT = "Hsport";
    private static final int HASHITERATIONS = 10;
    public static String creaMd5pwd(String pwd){
        SimpleHash hash = new SimpleHash("MD5", pwd, SALT, HASHITERATIONS);
        return hash.toHex();
    }
    public static void main(String[] args){
        System.out.println(creaMd5pwd("123"));
    }
}
