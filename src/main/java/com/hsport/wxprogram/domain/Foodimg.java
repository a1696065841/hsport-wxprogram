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
@TableName("t_foodimg")
public class Foodimg extends Model<Foodimg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String foodImgUrl;
    private Integer userID;
    private Integer todayintakeplanID;


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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTodayintakeplanID() {
        return todayintakeplanID;
    }

    public void setTodayintakeplanID(Integer todayintakeplanID) {
        this.todayintakeplanID = todayintakeplanID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Foodimg{" +
        ", id=" + id +
        ", foodImgUrl=" + foodImgUrl +
        ", userID=" + userID +
        ", todayintakeplanID=" + todayintakeplanID +
        "}";
    }
}
