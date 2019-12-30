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
@TableName("t_ysxg")
public class Ysxg extends Model<Ysxg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 三餐是否规律
     */
    private Integer sancanGL;
    /**
     * 平时是否吃早饭
     */
    private Integer psSFczf;
    /**
     * 口味偏好
     */
    private String kouweiPH;
    /**
     * 饮品偏好
     */
    private String yinpingPH;
    /**
     * 饮食类型偏好
     */
    private String yinshiPH;
    /**
     * 日饮水量
     */
    private Integer riYSL;
    /**
     * 平时是否吃零食
     */
    private Integer psSFcls;
    /**
     * 就餐地点
     */
    private String jcDidian;
    /**
     * 是否节食过
     */
    private Integer sfJSguo;
    /**
     * 是否对某些食物过敏
     */
    private Integer sfSWgm;
    /**
     * 平时是否饮酒
     */
    private Integer psSFyj;
    private Long userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSancanGL() {
        return sancanGL;
    }

    public void setSancanGL(Integer sancanGL) {
        this.sancanGL = sancanGL;
    }

    public Integer getPsSFczf() {
        return psSFczf;
    }

    public void setPsSFczf(Integer psSFczf) {
        this.psSFczf = psSFczf;
    }

    public String getKouweiPH() {
        return kouweiPH;
    }

    public void setKouweiPH(String kouweiPH) {
        this.kouweiPH = kouweiPH;
    }

    public String getYinpingPH() {
        return yinpingPH;
    }

    public void setYinpingPH(String yinpingPH) {
        this.yinpingPH = yinpingPH;
    }

    public String getYinshiPH() {
        return yinshiPH;
    }

    public void setYinshiPH(String yinshiPH) {
        this.yinshiPH = yinshiPH;
    }

    public Integer getRiYSL() {
        return riYSL;
    }

    public void setRiYSL(Integer riYSL) {
        this.riYSL = riYSL;
    }

    public Integer getPsSFcls() {
        return psSFcls;
    }

    public void setPsSFcls(Integer psSFcls) {
        this.psSFcls = psSFcls;
    }

    public String getJcDidian() {
        return jcDidian;
    }

    public void setJcDidian(String jcDidian) {
        this.jcDidian = jcDidian;
    }

    public Integer getSfJSguo() {
        return sfJSguo;
    }

    public void setSfJSguo(Integer sfJSguo) {
        this.sfJSguo = sfJSguo;
    }

    public Integer getSfSWgm() {
        return sfSWgm;
    }

    public void setSfSWgm(Integer sfSWgm) {
        this.sfSWgm = sfSWgm;
    }

    public Integer getPsSFyj() {
        return psSFyj;
    }

    public void setPsSFyj(Integer psSFyj) {
        this.psSFyj = psSFyj;
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
        return "Ysxg{" +
        ", id=" + id +
        ", sancanGL=" + sancanGL +
        ", psSFczf=" + psSFczf +
        ", kouweiPH=" + kouweiPH +
        ", yinpingPH=" + yinpingPH +
        ", yinshiPH=" + yinshiPH +
        ", riYSL=" + riYSL +
        ", psSFcls=" + psSFcls +
        ", jcDidian=" + jcDidian +
        ", sfJSguo=" + sfJSguo +
        ", sfSWgm=" + sfSWgm +
        ", psSFyj=" + psSFyj +
        ", userID=" + userID +
        "}";
    }
}
