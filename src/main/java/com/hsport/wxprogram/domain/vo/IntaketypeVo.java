package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.domain.Intaketype;

import java.util.List;

public class IntaketypeVo {

    private List<Food> foodList;
    private String intaketypeName;
    private Integer intaketypeID;

    public Integer getIntaketypeID() {
        return intaketypeID;
    }

    public void setIntaketypeID(Integer intaketypeID) {
        this.intaketypeID = intaketypeID;
    }

    public String getIntaketypeName() {
        return intaketypeName;
    }

    public void setIntaketypeName(String intaketypeName) {
        this.intaketypeName = intaketypeName;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }


}
