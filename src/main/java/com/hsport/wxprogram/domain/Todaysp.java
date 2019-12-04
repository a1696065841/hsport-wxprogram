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
@TableName("t_todaysp")
public class Todaysp extends Model<Todaysp> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 今日计划消耗卡路里
     */
    private Integer todayBurnCaloriePlan;
    /**
     * 今日实际消耗
     */
    private Integer todayBurnCalorie;
    private String date;
    /**
     * 对应总计划id
     */
    private Integer sportsPlanID;

    private Integer coachID;

    private Integer userID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCoachID() {
        return coachID;
    }

    public void setCoachID(Integer coachID) {
        this.coachID = coachID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTodayBurnCaloriePlan() {
        return todayBurnCaloriePlan;
    }

    public void setTodayBurnCaloriePlan(Integer todayBurnCaloriePlan) {
        this.todayBurnCaloriePlan = todayBurnCaloriePlan;
    }

    public Integer getTodayBurnCalorie() {
        return todayBurnCalorie;
    }

    public void setTodayBurnCalorie(Integer todayBurnCalorie) {
        this.todayBurnCalorie = todayBurnCalorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSportsPlanID() {
        return sportsPlanID;
    }

    public void setSportsPlanID(Integer sportsPlanID) {
        this.sportsPlanID = sportsPlanID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Todaysp{" +
        ", id=" + id +
        ", todayBurnCaloriePlan=" + todayBurnCaloriePlan +
        ", todayBurnCalorie=" + todayBurnCalorie +
        ", date=" + date +
        ", sportsPlanID=" + sportsPlanID +
        "}";
    }
}
