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
@TableName("t_sportsprogram")
public class Sportsprogram extends Model<Sportsprogram> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String scheduleMov;
    private String avgOfWeekExercise;
    private String sportsHobby;
    private String sportsHurt;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleMov() {
        return scheduleMov;
    }

    public void setScheduleMov(String scheduleMov) {
        this.scheduleMov = scheduleMov;
    }

    public String getAvgOfWeekExercise() {
        return avgOfWeekExercise;
    }

    public void setAvgOfWeekExercise(String avgOfWeekExercise) {
        this.avgOfWeekExercise = avgOfWeekExercise;
    }

    public String getSportsHobby() {
        return sportsHobby;
    }

    public void setSportsHobby(String sportsHobby) {
        this.sportsHobby = sportsHobby;
    }

    public String getSportsHurt() {
        return sportsHurt;
    }

    public void setSportsHurt(String sportsHurt) {
        this.sportsHurt = sportsHurt;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sportsprogram{" +
        ", id=" + id +
        ", scheduleMov=" + scheduleMov +
        ", avgOfWeekExercise=" + avgOfWeekExercise +
        ", sportsHobby=" + sportsHobby +
        ", sportsHurt=" + sportsHurt +
        ", userID=" + userID +
        "}";
    }
}
