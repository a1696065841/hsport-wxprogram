package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.domain.Stageplan;

import java.util.List;

public class SportsplanVo {
    private Sportsplan sportsplan;
    private List<Stageplan> stageplan;

    public Sportsplan getSportsplan() {
        return sportsplan;
    }

    public void setSportsplan(Sportsplan sportsplan) {
        this.sportsplan = sportsplan;
    }

    public List<Stageplan> getStageplan() {
        return stageplan;
    }

    public void setStageplan(List<Stageplan> stageplan) {
        this.stageplan = stageplan;
    }
}
