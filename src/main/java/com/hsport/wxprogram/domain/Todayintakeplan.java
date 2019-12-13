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
@TableName("t_todayintakeplan")
public class Todayintakeplan extends Model<Todayintakeplan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer breakfastIntake;
    private Integer lunchIntake;
    private Integer dinnerIntake;
    private Integer mealAdditionIntake;

    public Integer getCelluloseIntake() {
        return celluloseIntake;
    }

    public void setCelluloseIntake(Integer celluloseIntake) {
        this.celluloseIntake = celluloseIntake;
    }

    public Integer getCarbonIntake() {
        return carbonIntake;
    }

    public void setCarbonIntake(Integer carbonIntake) {
        this.carbonIntake = carbonIntake;
    }

    public Integer getProIntake() {
        return proIntake;
    }

    public void setProIntake(Integer proIntake) {
        this.proIntake = proIntake;
    }

    /**
     * 计划摄入上限
     */
    private Integer intakePlan;
    /**
     * 三大营养素摄入
     * */
    private Integer celluloseIntake;
    private Integer carbonIntake;
    private Integer proIntake;

    private Integer userID;
    private String date;
    /**
     * 实际摄入
     */
    private Integer sportsPlanID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntakePlan() {
        return intakePlan;
    }

    public void setIntakePlan(Integer intakePlan) {
        this.intakePlan = intakePlan;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
        return "Todayintakeplan{" +
                ", id=" + id +
                ", intakePlan=" + intakePlan +
                ", userID=" + userID +
                ", date=" + date +
                ", sportsPlanID=" + sportsPlanID +
                "}";
    }

    public Integer getBreakfastIntake() {
        return breakfastIntake;
    }

    public void setBreakfastIntake(Integer breakfastIntake) {
        this.breakfastIntake = breakfastIntake;
    }

    public Integer getLunchIntake() {
        return lunchIntake;
    }

    public void setLunchIntake(Integer lunchIntake) {
        this.lunchIntake = lunchIntake;
    }

    public Integer getDinnerIntake() {
        return dinnerIntake;
    }

    public void setDinnerIntake(Integer dinnerIntake) {
        this.dinnerIntake = dinnerIntake;
    }

    public Integer getMealAdditionIntake() {
        return mealAdditionIntake;
    }

    public void setMealAdditionIntake(Integer mealAdditionIntake) {
        this.mealAdditionIntake = mealAdditionIntake;
    }
}
