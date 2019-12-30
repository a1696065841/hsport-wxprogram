package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
@TableName("t_gym")
public class Gym extends Model<Gym> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("gym_address")
    private String gymAddress;
    @TableField("gym_phone")
    private String gymPhone;
    @TableField("gym_mark")
    private String gymMark;
    private String xCoordinate;
    private String yCoordinate;
    private String gym_name;
    private String gymDate;

    public String getGymDate() {
        return gymDate;
    }

    public void setGymDate(String gymDate) {
        this.gymDate = gymDate;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    private String gym_url;
    private Integer region_id;

    public String getGym_url() {
        return gym_url;
    }

    public void setGym_url(String gym_url) {
        this.gym_url = gym_url;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGymAddress() {
        return gymAddress;
    }

    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    public String getGymPhone() {
        return gymPhone;
    }

    public void setGymPhone(String gymPhone) {
        this.gymPhone = gymPhone;
    }

    public String getGymMark() {
        return gymMark;
    }

    public void setGymMark(String gymMark) {
        this.gymMark = gymMark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Gym{" +
                ", id=" + id +
                ", gymAddress=" + gymAddress +
                ", gymPhone=" + gymPhone +
                ", gymMark=" + gymMark +
                "}";
    }
}
