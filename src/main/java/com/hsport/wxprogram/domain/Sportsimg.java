package com.hsport.wxprogram.domain;

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
@TableName("t_sportsimg")
public class Sportsimg extends Model<Sportsimg> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String sportsImgUrl;
    private Integer userID;
    private Integer todayspID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportsImgUrl() {
        return sportsImgUrl;
    }

    public void setSportsImgUrl(String sportsImgUrl) {
        this.sportsImgUrl = sportsImgUrl;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTodayspID() {
        return todayspID;
    }

    public void setTodayspID(Integer todayspID) {
        this.todayspID = todayspID;
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
        ", todayspID=" + todayspID +
        "}";
    }
}
