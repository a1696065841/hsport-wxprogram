package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.domain.Intaketype;

import java.util.List;

public class IntaketypeVo {
    private  Intaketype intaketype;
    private List<Food> foodList;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Intaketype getIntaketype() {

        return intaketype;
    }

    public void setIntaketype(Intaketype intaketype) {
        this.intaketype = intaketype;
    }
}
