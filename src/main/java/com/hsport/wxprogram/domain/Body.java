package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2019-11-25
 */
@TableName("t_body")
public class Body extends Model<Body> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer height;
    private Double weight;
    private Double maxWeight;
    private Double bmi;

    public Double getWaistHipRatio() {
        return waistHipRatio;
    }

    public void setWaistHipRatio(Double waistHipRatio) {
        this.waistHipRatio = waistHipRatio;
    }
    /**
     * 腰臀比
     */
    private Double waistHipRatio;
    /**
     * 目标体重
     */
    private Double targetWeight;
    /**
     * 腰围
     */
    private Integer waistline;
    /**
     * 臀围
     */
    private Integer hipline;
    /**
     * 小腿围度
     */
    private Integer calfGirth;
    /**
     * 大腿围度
     */
    private Integer thighCircum;

    public Integer getArmCircum() {
        return armCircum;
    }

    public void setArmCircum(Integer armCircum) {
        this.armCircum = armCircum;
    }

    /**
     * 臂围

     */
    private Integer armCircum;

//
//    public User getUserID() {
//        return userID;
//    }
//
//    public void setUserID(User userID) {
//        this.userID = userID;
//    }
//
//    private User userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    private Long userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Integer getWaistline() {
        return waistline;
    }

    public void setWaistline(Integer waistline) {
        this.waistline = waistline;
    }

    public Integer getHipline() {
        return hipline;
    }

    public void setHipline(Integer hipline) {
        this.hipline = hipline;
    }

    public Integer getCalfGirth() {
        return calfGirth;
    }

    public void setCalfGirth(Integer calfGirth) {
        this.calfGirth = calfGirth;
    }

    public Integer getThighCircum() {
        return thighCircum;
    }

    public void setThighCircum(Integer thighCircum) {
        this.thighCircum = thighCircum;
    }



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Body{" +
        "id=" + id +
        ", height=" + height +
        ", weight=" + weight +
        ", maxWeight=" + maxWeight +
        ", bmi=" + bmi +
        ", targetWeight=" + targetWeight +
        ", waistline=" + waistline +
        ", hipline=" + hipline +
        ", calfGirth=" + calfGirth +
        ", thighCircum=" + thighCircum +
        ", userID=" + userID +
        "}";
    }
}
