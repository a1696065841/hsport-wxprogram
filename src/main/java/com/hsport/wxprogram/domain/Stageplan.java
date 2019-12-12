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
 * @since 2019-12-12
 */
@TableName("t_stageplan")
public class Stageplan extends Model<Stageplan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 计划的第几个周期
     */
    private Integer stageOfCycle;
    /**
     * 阶段时间
     */
    private String stageTime;
    /**
     * 阶段目标
     */
    private String stageTarget;
    private Integer userID;
    /**
     * 锻炼时长
     */
    private Integer durationOfExercise;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStageOfCycle() {
        return stageOfCycle;
    }

    public void setStageOfCycle(Integer stageOfCycle) {
        this.stageOfCycle = stageOfCycle;
    }

    public String getStageTime() {
        return stageTime;
    }

    public void setStageTime(String stageTime) {
        this.stageTime = stageTime;
    }

    public String getStageTarget() {
        return stageTarget;
    }

    public void setStageTarget(String stageTarget) {
        this.stageTarget = stageTarget;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getDurationOfExercise() {
        return durationOfExercise;
    }

    public void setDurationOfExercise(Integer durationOfExercise) {
        this.durationOfExercise = durationOfExercise;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Stageplan{" +
        ", id=" + id +
        ", stageOfCycle=" + stageOfCycle +
        ", stageTime=" + stageTime +
        ", stageTarget=" + stageTarget +
        ", userID=" + userID +
        ", durationOfExercise=" + durationOfExercise +
        "}";
    }
}
