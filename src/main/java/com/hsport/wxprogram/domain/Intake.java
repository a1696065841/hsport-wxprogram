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
 * @since 2019-11-26
 */
@TableName("t_intake")
public class Intake extends Model<Intake> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer intakeCalories;
    private Integer foodWeight;
    /**
     * 蛋白质百分比
     */
    private Integer proPer;
    private Integer CarbonIntake;
    private Integer cellulosePer;
    private Integer waterIntake;
    private Integer sportsPlanID;


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

    public Integer getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(Integer foodWeight) {
        this.foodWeight = foodWeight;
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
        return "Intake{" +
        ", id=" + id +
        ", intakeCalories=" + intakeCalories +
        ", foodWeight=" + foodWeight +
        ", proPer=" + proPer +
        ", CarbonIntake=" + CarbonIntake +
        ", cellulosePer=" + cellulosePer +
        ", waterIntake=" + waterIntake +
        ", sportsPlanID=" + sportsPlanID +
        "}";
    }
}
