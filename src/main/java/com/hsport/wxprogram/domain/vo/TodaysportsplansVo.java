package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Sportsimg;
import com.hsport.wxprogram.domain.Todaysportsplans;

import java.util.List;

public class TodaysportsplansVo {
    private List<Todaysportsplans> todaysportsplans;
    private List<Sportsimg> sportsimgs;

    public List<Todaysportsplans> getTodaysportsplans() {
        return todaysportsplans;
    }

    public void setTodaysportsplans(List<Todaysportsplans> todaysportsplans) {
        this.todaysportsplans = todaysportsplans;
    }

    public List<Sportsimg> getSportsimgs() {
        return sportsimgs;
    }

    public void setSportsimgs(List<Sportsimg> sportsimgs) {
        this.sportsimgs = sportsimgs;
    }
}
