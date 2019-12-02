package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@TableName("t_food")
public class Food extends Model<Food> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String foodName;
    /**
     * 早中晚餐或者加餐
     */
    private String foodType;
    /**
     * 每一百g蛋白百分比
     */
    private Integer foodPro;
    /**
     * 碳水
     */
    private Integer carbon;
    /**
     * 纤维素百分比
     */
    private Integer xianweisu;
    private Integer todayIntakePlanID;
    /**
     * 食物分量_一杯_100g都可以
     */
    private String foodWeight;
    /**
     * 食物热量
     */
    private Integer foodCalories;
    /**
     * 食物脂肪含量
     */
    private Integer foodFat;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Integer getFoodPro() {
        return foodPro;
    }

    public void setFoodPro(Integer foodPro) {
        this.foodPro = foodPro;
    }

    public Integer getCarbon() {
        return carbon;
    }

    public void setCarbon(Integer carbon) {
        this.carbon = carbon;
    }

    public Integer getXianweisu() {
        return xianweisu;
    }

    public void setXianweisu(Integer xianweisu) {
        this.xianweisu = xianweisu;
    }

    public Integer getTodayIntakePlanID() {
        return todayIntakePlanID;
    }

    public void setTodayIntakePlanID(Integer todayIntakePlanID) {
        this.todayIntakePlanID = todayIntakePlanID;
    }

    public String getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(String foodWeight) {
        this.foodWeight = foodWeight;
    }

    public Integer getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(Integer foodCalories) {
        this.foodCalories = foodCalories;
    }

    public Integer getFoodFat() {
        return foodFat;
    }

    public void setFoodFat(Integer foodFat) {
        this.foodFat = foodFat;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Food{" +
        ", id=" + id +
        ", foodName=" + foodName +
        ", foodType=" + foodType +
        ", foodPro=" + foodPro +
        ", carbon=" + carbon +
        ", xianweisu=" + xianweisu +
        ", todayIntakePlanID=" + todayIntakePlanID +
        ", foodWeight=" + foodWeight +
        ", foodCalories=" + foodCalories +
        ", foodFat=" + foodFat +
        ", userID=" + userID +
        "}";
    }
}
