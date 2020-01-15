package com.hsport.wxprogram.common.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;

public class MD5Util {private static String byteArrayToHexString(byte b[]) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++)
        resultSb.append(byteToHexString(b[i]));

    return resultSb.toString();
}

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    public static final String SALT = "Hsport";
    private static final int HASHITERATIONS = 10;
    public static String creaMd5pwd(String pwd){
        SimpleHash hash = new SimpleHash("MD5", pwd, SALT, HASHITERATIONS);
        return hash.toHex();
    }

    public static String loginMd5pwd(String pwd){
        SimpleHash hash = new SimpleHash("MD5", pwd, SALT, HASHITERATIONS);
        return   hash.getAlgorithmName();

    }
    public static void main(String[] args){
        System.out.println(creaMd5pwd("qinglizi123"));
    }
}
