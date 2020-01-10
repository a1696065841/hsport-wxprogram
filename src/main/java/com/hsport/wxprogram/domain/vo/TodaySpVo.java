package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Todaysp;
import com.hsport.wxprogram.domain.Todaysportsplans;

import java.util.List;

public class TodaySpVo {
    private Todaysp todaysp;
    private List<Todaysportsplans> todaysportsplans;

    public Todaysp getTodaysp() {
        return todaysp;
    }

    public void setTodaysp(Todaysp todaysp) {
        this.todaysp = todaysp;
    }

    public  List<Todaysportsplans> getTodaysportsplans() {
        return todaysportsplans;
    }

    public void setTodaysportsplans( List<Todaysportsplans> todaysportsplans) {
        this.todaysportsplans = todaysportsplans;
    }
}
