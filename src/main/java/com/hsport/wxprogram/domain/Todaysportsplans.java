package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("t_todaysportsplans")
public class Todaysportsplans extends Model<Todaysportsplans> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 运动名
     */
    private String planName;
    //这个完成百分比
    private int planPer;

    public int getPlanPer() {
        return planPer;
    }

    public void setPlanPer(int planPer) {
        this.planPer = planPer;
    }

    /**
     * 运动形式,类似走多少步
     */
    private String planType;
    /**
     * 完成进度
     */
    private String plansSchedule;

    /**
     * 今日运动计划id
     */
    @TableField("todaySP_id")
    private Integer todayspId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlansSchedule() {
        return plansSchedule;
    }

    public void setPlansSchedule(String plansSchedule) {
        this.plansSchedule = plansSchedule;
    }



    public Integer getTodayspId() {
        return todayspId;
    }

    public void setTodayspId(Integer todayspId) {
        this.todayspId = todayspId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Todaysportsplans{" +
        ", id=" + id +
        ", planName=" + planName +
        ", planType=" + planType +
        ", plansSchedule=" + plansSchedule +
        ", todayspId=" + todayspId +
        "}";
    }
}
