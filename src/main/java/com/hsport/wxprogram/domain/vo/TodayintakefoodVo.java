package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.domain.Todayintakefood;

import java.util.List;

public class TodayintakefoodVo {
    private List<Todayintakefood> todayintakefood;
    private List<Foodimg> foodimgs;
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<Todayintakefood> getTodayintakefood() {
        return todayintakefood;
    }

    public void setTodayintakefood(List<Todayintakefood> todayintakefood) {
        this.todayintakefood = todayintakefood;
    }

    public List<Foodimg> getFoodimgs() {
        return foodimgs;
    }

    public void setFoodimgs(List<Foodimg> foodimgs) {
        this.foodimgs = foodimgs;
    }
}
