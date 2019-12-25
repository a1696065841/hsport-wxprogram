package com.hsport.wxprogram.common.util;

public class CharToString {
    public static String C2S(String strings[]){
        StringBuffer stringBuffer = new StringBuffer();
        if (strings==null||strings.length==0){
            return null;
        }else {
            for (String c : strings) {
                if (c!=strings[strings.length-1]){
                    stringBuffer.append(c+",");
                }else {
                    stringBuffer.append(c);
                }
            }
            return stringBuffer.toString();
        }
    }
}
