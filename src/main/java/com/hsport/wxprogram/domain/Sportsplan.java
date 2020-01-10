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
 * @since 2019-11-27
 */
@TableName("t_sportsplan")
public class Sportsplan extends Model<Sportsplan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    public Integer getAvgPlanFat() {
        return avgPlanFat;
    }

    public void setAvgPlanFat(Integer avgPlanFat) {
        this.avgPlanFat = avgPlanFat;
    }

    private Integer avgPlanFat;
    /**
     * 计划目标
     */
    private String planObjectives;
    /**
     * 计划完成天数
     */
    private Integer planUseDays;
    private Long userID;
    /**
     * 运动计划总消耗
     */
    private Integer masterPlanBurn;
    /**
     * 运动计划总摄入
     */
    private Integer masterPlanIntake;
    private String planStratTime;
    private Integer planType;
    private String planEndDate;
    private Integer coachID;
    private Integer avgPlanCellulose;
    private Integer avgPlanCarbon;
    private Integer avgPlanPro;
    private Integer avgPlanIntake;

    public Integer getAvgPlanIntake() {
        return avgPlanIntake;
    }

    public void setAvgPlanIntake(Integer avgPlanIntake) {
        this.avgPlanIntake = avgPlanIntake;
    }

    public Integer getAvgPlanCellulose() {
        return avgPlanCellulose;
    }

    public void setAvgPlanCellulose(Integer avgPlanCellulose) {
        this.avgPlanCellulose = avgPlanCellulose;
    }

    public Integer getAvgPlanCarbon() {
        return avgPlanCarbon;
    }

    public void setAvgPlanCarbon(Integer avgPlanCarbon) {
        this.avgPlanCarbon = avgPlanCarbon;
    }

    public Integer getAvgPlanPro() {
        return avgPlanPro;
    }

    public void setAvgPlanPro(Integer avgPlanPro) {
        this.avgPlanPro = avgPlanPro;
    }

    public Integer getCoachID() {
        return coachID;
    }

    public void setCoachID(Integer coachID) {
        this.coachID = coachID;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanObjectives() {
        return planObjectives;
    }

    public void setPlanObjectives(String planObjectives) {
        this.planObjectives = planObjectives;
    }

    public Integer getPlanUseDays() {
        return planUseDays;
    }

    public void setPlanUseDays(Integer planUseDays) {
        this.planUseDays = planUseDays;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getMasterPlanBurn() {
        return masterPlanBurn;
    }

    public void setMasterPlanBurn(Integer masterPlanBurn) {
        this.masterPlanBurn = masterPlanBurn;
    }

    public Integer getMasterPlanIntake() {
        return masterPlanIntake;
    }

    public void setMasterPlanIntake(Integer masterPlanIntake) {
        this.masterPlanIntake = masterPlanIntake;
    }

    public String getPlanStratTime() {
        return planStratTime;
    }

    public void setPlanStratTime(String planStratTime) {
        this.planStratTime = planStratTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sportsplan{" +
                ", id=" + id +
                ", planObjectives=" + planObjectives +
                ", planUseDays=" + planUseDays +
                ", userID=" + userID +
                ", masterPlanBurn=" + masterPlanBurn +
                ", masterPlanIntake=" + masterPlanIntake +
                ", planStratTime=" + planStratTime +
                ",coachID=" + coachID +
                "}";
    }
}
