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
     * 计划周期名字
     */
    private String stageOfCycle;

    public Integer getStageTimeStart() {
        return stageTimeStart;
    }

    public void setStageTimeStart(Integer stageTimeStart) {
        this.stageTimeStart = stageTimeStart;
    }

    public Integer getStageTimeEnd() {
        return stageTimeEnd;
    }

    public void setStageTimeEnd(Integer stageTimeEnd) {
        this.stageTimeEnd = stageTimeEnd;
    }

    /**
     * 阶段时间
     */
    private Integer stageTimeStart;
    private Integer stageTimeEnd;

    /**
     * 阶段目标
     */
    private String stageTarget;
    private Integer sportsplanID;
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

    public String getStageOfCycle() {
        return stageOfCycle;
    }

    public void setStageOfCycle(String stageOfCycle) {
        this.stageOfCycle = stageOfCycle;
    }

    public String getStageTarget() {
        return stageTarget;
    }

    public void setStageTarget(String stageTarget) {
        this.stageTarget = stageTarget;
    }

    public Integer getSportsplanID() {
        return sportsplanID;
    }

    public void setSportsplanID(Integer sportsplanID) {
        this.sportsplanID = sportsplanID;
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
        ", stageTime=" + stageTimeStart +
        ", stageTarget=" + stageTarget +
        ", sportsplanID=" + sportsplanID +
        ", durationOfExercise=" + durationOfExercise +
        "}";
    }
}
