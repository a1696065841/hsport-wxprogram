package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2019-11-21
 */
@TableName("t_burncalories")
public class Burncalories extends Model<Burncalories> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer BurnCalories;
    private String BurnType;
    private Integer sportsBpm;
    private Integer lifeBpm;
    private Integer sportsPlanID;
    /**
     * 运动强度
     */
    private Integer SportsQD;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBurnCalories() {
        return BurnCalories;
    }

    public void setBurnCalories(Integer BurnCalories) {
        this.BurnCalories = BurnCalories;
    }

    public String getBurnType() {
        return BurnType;
    }

    public void setBurnType(String BurnType) {
        this.BurnType = BurnType;
    }

    public Integer getSportsBpm() {
        return sportsBpm;
    }

    public void setSportsBpm(Integer sportsBpm) {
        this.sportsBpm = sportsBpm;
    }

    public Integer getLifeBpm() {
        return lifeBpm;
    }

    public void setLifeBpm(Integer lifeBpm) {
        this.lifeBpm = lifeBpm;
    }

    public Integer getSportsPlanID() {
        return sportsPlanID;
    }

    public void setSportsPlanID(Integer sportsPlanID) {
        this.sportsPlanID = sportsPlanID;
    }

    public Integer getSportsQD() {
        return SportsQD;
    }

    public void setSportsQD(Integer SportsQD) {
        this.SportsQD = SportsQD;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Burncalories{" +
        ", id=" + id +
        ", BurnCalories=" + BurnCalories +
        ", BurnType=" + BurnType +
        ", sportsBpm=" + sportsBpm +
        ", lifeBpm=" + lifeBpm +
        ", sportsPlanID=" + sportsPlanID +
        ", SportsQD=" + SportsQD +
        "}";
    }
}
