package com.hsport.wxprogram.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String todaySix(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);

        c.set(Calendar.MINUTE, 0);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }

    public static String yesterdaySix(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.DATE,-1);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
    public static String tommrowSix(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.DATE,1);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
    public static String AfterTommrowSix(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.DATE,2);
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
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
    //现在
    public static String now(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Calendar c=Calendar.getInstance();
        String date=simpleDateFormat.format(c.getTime());
        return date;
    }
    public static int DateCompare(String source, String traget, String type) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date sourcedate = format.parse(source);
        Date tragetdate = format.parse(traget);
        int i = sourcedate.compareTo(tragetdate);
        return i;
    }
    public static boolean isTodayPlanTime() throws Exception {
        return DateUtil.DateCompare(DateUtil.now(),DateUtil.todaySix(),"yyyy.MM.dd HH:mm")==-1;
    }

}
