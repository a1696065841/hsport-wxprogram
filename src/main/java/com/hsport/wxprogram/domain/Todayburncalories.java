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
@TableName("t_todayburncalories")
public class Todayburncalories extends Model<Todayburncalories> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 运动消耗完成百分比
     */
    private Integer burnCaloriesPer;
    /**
     * 消耗卡路里量
     */
    private Integer BurnCalories;
    private Integer sportsBpm;
    /**
     * 日常心率
     */
    private Integer lifeBpm;
    /**
     * 运动强度
     */
    private Integer SportsQD;
    private Integer dayWeight;
    private String dayMood;
    /**
     * 当日消耗量
     */
    private Integer dayBurns;
    private Integer sportsPlanID;
    /**
     * 私教训练完成百分比
     */
    private Integer coachPlanPer;
    /**
     * 具体私教时间
     */
    private Integer coachTimes;
    /**
     * 有氧运动时间
     */
    private Integer aerobicTimes;
    /**
     * 当日日期
     */
    private String date;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBurnCaloriesPer() {
        return burnCaloriesPer;
    }

    public void setBurnCaloriesPer(Integer burnCaloriesPer) {
        this.burnCaloriesPer = burnCaloriesPer;
    }

    public Integer getBurnCalories() {
        return BurnCalories;
    }

    public void setBurnCalories(Integer BurnCalories) {
        this.BurnCalories = BurnCalories;
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

    public Integer getSportsQD() {
        return SportsQD;
    }

    public void setSportsQD(Integer SportsQD) {
        this.SportsQD = SportsQD;
    }

    public Integer getDayWeight() {
        return dayWeight;
    }

    public void setDayWeight(Integer dayWeight) {
        this.dayWeight = dayWeight;
    }

    public String getDayMood() {
        return dayMood;
    }

    public void setDayMood(String dayMood) {
        this.dayMood = dayMood;
    }

    public Integer getDayBurns() {
        return dayBurns;
    }

    public void setDayBurns(Integer dayBurns) {
        this.dayBurns = dayBurns;
    }

    public Integer getSportsPlanID() {
        return sportsPlanID;
    }

    public void setSportsPlanID(Integer sportsPlanID) {
        this.sportsPlanID = sportsPlanID;
    }

    public Integer getCoachPlanPer() {
        return coachPlanPer;
    }

    public void setCoachPlanPer(Integer coachPlanPer) {
        this.coachPlanPer = coachPlanPer;
    }

    public Integer getCoachTimes() {
        return coachTimes;
    }

    public void setCoachTimes(Integer coachTimes) {
        this.coachTimes = coachTimes;
    }

    public Integer getAerobicTimes() {
        return aerobicTimes;
    }

    public void setAerobicTimes(Integer aerobicTimes) {
        this.aerobicTimes = aerobicTimes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "Todayburncalories{" +
        ", id=" + id +
        ", burnCaloriesPer=" + burnCaloriesPer +
        ", BurnCalories=" + BurnCalories +
        ", sportsBpm=" + sportsBpm +
        ", lifeBpm=" + lifeBpm +
        ", SportsQD=" + SportsQD +
        ", dayWeight=" + dayWeight +
        ", dayMood=" + dayMood +
        ", dayBurns=" + dayBurns +
        ", sportsPlanID=" + sportsPlanID +
        ", coachPlanPer=" + coachPlanPer +
        ", coachTimes=" + coachTimes +
        ", aerobicTimes=" + aerobicTimes +
        ", date=" + date +
        ", userID=" + userID +
        "}";
    }
}
