package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@TableName("t_sportsimg")
public class Sportsimg extends Model<Sportsimg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String sportsImgUrl;
    private Long userID;
    private String date;
    private String burnType;
    private Integer productserviceID;
    private Integer burnCalories;
    private boolean isUsed;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
    public Integer getProductserviceID() {
        return productserviceID;
    }

    public void setProductserviceID(Integer productserviceID) {
        this.productserviceID = productserviceID;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBurnType() {
        return burnType;
    }

    public void setBurnType(String burnType) {
        this.burnType = burnType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBurnCalories() {
        return burnCalories;
    }

    public void setBurnCalories(Integer burnCalories) {
        this.burnCalories = burnCalories;
    }


    public Integer getId() {
        return id;
    }

    public String getSportsImgUrl() {
        return sportsImgUrl;
    }

    public void setSportsImgUrl(String sportsImgUrl) {
        this.sportsImgUrl = sportsImgUrl;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sportsimg{" +
                ", id=" + id +
                ", sportsImgUrl=" + sportsImgUrl +
                ", userID=" + userID +
                "}";
    }
}
