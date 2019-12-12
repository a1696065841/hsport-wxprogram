package com.hsport.wxprogram.common.util;

public class CharToString {
    public static String C2S(char chars[]){
        StringBuffer stringBuffer = new StringBuffer();
        if (chars==null||chars.length==0){
            return null;
        }else {
            for (char c : chars) {
                if (c!=chars[chars.length-1]){
                    stringBuffer.append(c+",");
                }else {
                    stringBuffer.append(c);
                }
            }
            return stringBuffer.toString();
        }
    }
}
