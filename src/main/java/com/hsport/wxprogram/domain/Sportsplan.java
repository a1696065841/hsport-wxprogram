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
@TableName("t_sportsplan")
public class Sportsplan extends Model<Sportsplan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 计划目标
     */
    private String planObjectives;
    /**
     * 计划完成天数
     */
    private Integer planUseDays;
    private Integer userID;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
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
        "}";
    }
}
