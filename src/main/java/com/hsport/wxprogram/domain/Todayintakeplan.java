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

    /**
     * 计划摄入上限
     */
    private Integer intakePlan;

    private Long userID;
    private String date;


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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                "}";
    }

}
