package com.hsport.wxprogram.service.impl;

import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

class RedisServiceImplTest {
    public static void main(String[] args) throws AWTException {
        Robot  r   =   new Robot();
        System.out.println( "延时前:"+new Date().toString()  );
        r.delay(   100   );
        System.out.println(   "延时后:"+new Date().toString()   );
    }
}