package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2019-12-23
 */
@TableName("t_intaketype")
public class Intaketype extends Model<Intaketype> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String intakePlanName;

    private Long userID;
    private String date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntakePlanName() {
        return intakePlanName;
    }

    public void setIntakePlanName(String intakePlanName) {
        this.intakePlanName = intakePlanName;
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
        return "Intaketype{" +
        ", id=" + id +
        ", intakePlanName=" + intakePlanName +
        ", userID=" + userID +
        ", date=" + date +
        "}";
    }
}
