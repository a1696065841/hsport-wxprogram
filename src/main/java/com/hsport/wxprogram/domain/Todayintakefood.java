package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lhb
 * @since 2020-01-09
 */
@TableName("t_todayintakefood")
public class Todayintakefood extends Model<Todayintakefood> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Long userID;
    private String foodName;
    private Integer foodPro;
    private String foodType;
    private Integer foodWeight;
    private Integer foodCalories;
    private Integer foodCellulose;
    private Integer foodCarbon;
    private String date;

    public void setFoodFat(Integer foodFat) {
        this.foodFat = foodFat;
    }

    private Integer foodFat;

    public void setFoodCarbon(Integer foodCarbon) {
        this.foodCarbon = foodCarbon;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPro() {
        return foodPro;
    }

    public void setFoodPro(Integer foodPro) {
        this.foodPro = foodPro;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Integer getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(Integer foodWeight) {
        this.foodWeight = foodWeight;
    }

    public Integer getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(Integer foodCalories) {
        this.foodCalories = foodCalories;
    }

    public Integer getFoodCellulose() {
        return foodCellulose;
    }

    public void setFoodCellulose(Integer foodCellulose) {
        this.foodCellulose = foodCellulose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Todayintakefood{" +
                ", id=" + id +
                ", userID=" + userID +
                ", foodName=" + foodName +
                ", foodPro=" + foodPro +
                ", foodType=" + foodType +
                ", foodWeight=" + foodWeight +
                ", foodCalories=" + foodCalories +
                ", foodCellulose=" + foodCellulose +
                ", date=" + date +
                "}";
    }

    public Integer getFoodCarbon() {
        return foodCarbon;
    }

    public Integer getFoodFat() {
        return foodFat;
    }
}
