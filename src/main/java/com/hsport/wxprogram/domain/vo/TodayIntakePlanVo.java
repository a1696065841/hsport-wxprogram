package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.domain.Intaketype;
import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.domain.Todayintakeplan;

import java.security.PrivateKey;
import java.util.List;

public class TodayIntakePlanVo {
    private Todayintakeplan todayintakeplan;

    private List<IntaketypeVo> intaketypeVoList;

    public Todayintakeplan getTodayintakeplan() {
        return todayintakeplan;
    }

    public List<IntaketypeVo> getIntaketypeVoList() {
        return intaketypeVoList;
    }

    public void setIntaketypeVoList(List<IntaketypeVo> intaketypeVoList) {
        this.intaketypeVoList = intaketypeVoList;
    }

    public void setTodayintakeplan(Todayintakeplan todayintakeplan) {

        this.todayintakeplan = todayintakeplan;
    }




    @Override
    public String toString() {
        return "todayIntakePlanVo{" +
                "todayintakeplan=" + todayintakeplan +
                ", intaketype=" + intaketypeVoList +
                '}';
    }
}
