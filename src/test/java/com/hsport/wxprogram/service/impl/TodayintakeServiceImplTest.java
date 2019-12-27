package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TodayintakeServiceImplTest {
    @Test
    void test() throws ParseException {
        String date="2017-7";
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
        Date parse = dateFormat.parse(date);
        System.out.println(parse);
    }
}