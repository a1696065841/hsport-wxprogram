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
 * @since 2019-11-27
 */
@TableName("t_todayintake")
public class Todayintake extends Model<Todayintake> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer intakeCalories;
    private Integer dayIntake;
    /**
     * 蛋白质百分比
     */
    private Integer proPer;
    /**
     * 碳水化合物摄入
     */
    private Integer CarbonIntake;
    /**
     * 纤维素百分比
     */
    private Integer cellulosePer;
    /**
     * 水摄入
     */
    private Integer waterIntake;
    /**
     * 当日日期
     */
    private String date;
    /**
     * 总运动计划ID
     */
    private Integer sportsPlanID;
    private Long userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntakeCalories() {
        return intakeCalories;
    }

    public void setIntakeCalories(Integer intakeCalories) {
        this.intakeCalories = intakeCalories;
    }

    public Integer getDayIntake() {
        return dayIntake;
    }

    public void setDayIntake(Integer dayIntake) {
        this.dayIntake = dayIntake;
    }

    public Integer getProPer() {
        return proPer;
    }

    public void setProPer(Integer proPer) {
        this.proPer = proPer;
    }

    public Integer getCarbonIntake() {
        return CarbonIntake;
    }

    public void setCarbonIntake(Integer CarbonIntake) {
        this.CarbonIntake = CarbonIntake;
    }

    public Integer getCellulosePer() {
        return cellulosePer;
    }

    public void setCellulosePer(Integer cellulosePer) {
        this.cellulosePer = cellulosePer;
    }

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
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
        return "Todayintake{" +
        ", id=" + id +
        ", intakeCalories=" + intakeCalories +
        ", dayIntake=" + dayIntake +
        ", proPer=" + proPer +
        ", CarbonIntake=" + CarbonIntake +
        ", cellulosePer=" + cellulosePer +
        ", waterIntake=" + waterIntake +
        ", date=" + date +
        ", sportsPlanID=" + sportsPlanID +
        ", userID=" + userID +
        "}";
    }
}
