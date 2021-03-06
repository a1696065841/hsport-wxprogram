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
 * @since 2019-11-28
 */
@TableName("t_foodimg")
public class Foodimg extends Model<Foodimg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String foodImgUrl;
    private Long userID;
    private String date;
    private String  foodType;
    private Integer foodPro;
    private Integer foodCarbon;
    private String foodName;
    private Integer foodWeight;
    private Integer foodCalories;
    private Integer foodCellulose;
    private boolean isUsed;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
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

    public Integer getFoodCarbon() {
        return foodCarbon;
    }

    public void setFoodCarbon(Integer foodCarbon) {
        this.foodCarbon = foodCarbon;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodImgUrl() {
        return foodImgUrl;
    }

    public void setFoodImgUrl(String foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
        return "Foodimg{" +
                "id=" + id +
                ", foodImgUrl='" + foodImgUrl + '\'' +
                ", userID=" + userID +
                ", date='" + date + '\'' +
                ", foodType=" + foodType +
                ", foodPro=" + foodPro +
                ", foodCarbon=" + foodCarbon +
                ", foodName='" + foodName + '\'' +
                ", foodWeight=" + foodWeight +
                ", foodCalories=" + foodCalories +
                ", foodCellulose=" + foodCellulose +
                '}';
    }
}
