package com.hsport.wxprogram.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //获取明天的时间
    public static String tommrow(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE,1);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
    //昨天的时间
    public static String yesterday(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE,-1);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
    //今天
    public static String today(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar c=Calendar.getInstance();
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
}
