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
 * @since 2019-11-29
 */
@TableName("t_ceyice")
public class Ceyice extends Model<Ceyice> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getYaowei() {
        return yaowei;
    }

    public void setYaowei(Integer yaowei) {
        this.yaowei = yaowei;
    }

    public Integer getTunwei() {
        return tunwei;
    }

    public void setTunwei(Integer tunwei) {
        this.tunwei = tunwei;
    }

    private Integer age;
    private double height;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double weight;
    private Integer yaowei;
    private Integer tunwei;


    /**
     * 父母是否有人比较胖
     */
    private Integer fmsfyrbjp;
    /**
     * 体重何时明显增加
     */
    private Integer tzhsmxzj;
    /**
     * 日常饮食情况多选题!!
     */
    private String rcysqkDX;
    /**
     * 饮食行为习惯
     */
    private String ysxwxgDX;
    private Integer sfyydxg;
    /**
     * 每周参加运动频率
     */
    private Integer mzcjydpl;
    /**
     * 运动持续时间
     */
    private Integer ydcxsj;
    /**
     * 部位运动损伤多选
     */
    private String buweiYdSunsDX;
    /**
     * 是否经常熬夜
     */
    private Integer sfjcay;
    /**
     * 平均睡眠时间
     */
    private Integer pjsmsj;
    private String rcsmzlDX;
    private String rcpbqkDX;
    /**
     * 以前有过的减肥经历多选
     */
    private String yqJianfeiJLDX;
    /**
     * 日常压力分析
     */
    private Integer rcylfx;
    /**
     * 当时减肥效果和反弹情况
     */
    private Integer dsjfqkFantan;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Long userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFmsfyrbjp() {
        return fmsfyrbjp;
    }

    public void setFmsfyrbjp(Integer fmsfyrbjp) {
        this.fmsfyrbjp = fmsfyrbjp;
    }

    public Integer getTzhsmxzj() {
        return tzhsmxzj;
    }

    public void setTzhsmxzj(Integer tzhsmxzj) {
        this.tzhsmxzj = tzhsmxzj;
    }

    public String getRcysqkDX() {
        return rcysqkDX;
    }

    public void setRcysqkDX(String rcysqkDX) {
        this.rcysqkDX = rcysqkDX;
    }

    public String getYsxwxgDX() {
        return ysxwxgDX;
    }

    public void setYsxwxgDX(String ysxwxgDX) {
        this.ysxwxgDX = ysxwxgDX;
    }

    public Integer getSfyydxg() {
        return sfyydxg;
    }

    public void setSfyydxg(Integer sfyydxg) {
        this.sfyydxg = sfyydxg;
    }

    public Integer getMzcjydpl() {
        return mzcjydpl;
    }

    public void setMzcjydpl(Integer mzcjydpl) {
        this.mzcjydpl = mzcjydpl;
    }

    public Integer getYdcxsj() {
        return ydcxsj;
    }

    public void setYdcxsj(Integer ydcxsj) {
        this.ydcxsj = ydcxsj;
    }

    public String getBuweiYdSunsDX() {
        return buweiYdSunsDX;
    }

    public void setBuweiYdSunsDX(String buweiYdSunsDX) {
        this.buweiYdSunsDX = buweiYdSunsDX;
    }

    public Integer getSfjcay() {
        return sfjcay;
    }

    public void setSfjcay(Integer sfjcay) {
        this.sfjcay = sfjcay;
    }

    public Integer getPjsmsj() {
        return pjsmsj;
    }

    public void setPjsmsj(Integer pjsmsj) {
        this.pjsmsj = pjsmsj;
    }

    public String getRcsmzlDX() {
        return rcsmzlDX;
    }

    public void setRcsmzlDX(String rcsmzlDX) {
        this.rcsmzlDX = rcsmzlDX;
    }

    public String getRcpbqkDX() {
        return rcpbqkDX;
    }

    public void setRcpbqkDX(String rcpbqkDX) {
        this.rcpbqkDX = rcpbqkDX;
    }

    public String getYqJianfeiJLDX() {
        return yqJianfeiJLDX;
    }

    public void setYqJianfeiJLDX(String yqJianfeiJLDX) {
        this.yqJianfeiJLDX = yqJianfeiJLDX;
    }

    public Integer getRcylfx() {
        return rcylfx;
    }

    public void setRcylfx(Integer rcylfx) {
        this.rcylfx = rcylfx;
    }

    public Integer getDsjfqkFantan() {
        return dsjfqkFantan;
    }

    public void setDsjfqkFantan(Integer dsjfqkFantan) {
        this.dsjfqkFantan = dsjfqkFantan;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ceyice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", yaowei=" + yaowei +
                ", tunwei=" + tunwei +
                ", fmsfyrbjp=" + fmsfyrbjp +
                ", tzhsmxzj=" + tzhsmxzj +
                ", rcysqkDX='" + rcysqkDX + '\'' +
                ", ysxwxgDX='" + ysxwxgDX + '\'' +
                ", sfyydxg=" + sfyydxg +
                ", mzcjydpl=" + mzcjydpl +
                ", ydcxsj=" + ydcxsj +
                ", buweiYdSunsDX='" + buweiYdSunsDX + '\'' +
                ", sfjcay=" + sfjcay +
                ", pjsmsj=" + pjsmsj +
                ", rcsmzlDX='" + rcsmzlDX + '\'' +
                ", rcpbqkDX='" + rcpbqkDX + '\'' +
                ", yqJianfeiJLDX='" + yqJianfeiJLDX + '\'' +
                ", rcylfx=" + rcylfx +
                ", dsjfqkFantan=" + dsjfqkFantan +
                ", userID=" + userID +
                '}';
    }
}
