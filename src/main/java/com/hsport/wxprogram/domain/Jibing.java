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
@TableName("t_jibing")
public class Jibing extends Model<Jibing> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 高血压
     */
    private Integer gxyLS;
    private Integer gxyXZ;
    /**
     * 半年内手术
     */
    private Integer banNianNeiShoushu;
    /**
     * 高血糖
     */
    private Integer gxt;
    private Integer dxt;
    /**
     * 血脂是否异常
     */
    private Integer xuezhiYC;
    /**
     * 痛风或尿酸高甲亢
     */
    private Integer tfORnsgORjk;
    /**
     * 甲kang
     */
    private Integer jiaKang;
    /**
     * 肠道问题
     */
    private Integer cangdaoWT;
    /**
     * 胆囊疾病
     */
    private Integer danNangWT;
    /**
     * 肾脏问题
     */
    private Integer shengzangWT;
    private Integer guanjieWT;
    private String qitaWT;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGxyLS() {
        return gxyLS;
    }

    public void setGxyLS(Integer gxyLS) {
        this.gxyLS = gxyLS;
    }

    public Integer getGxyXZ() {
        return gxyXZ;
    }

    public void setGxyXZ(Integer gxyXZ) {
        this.gxyXZ = gxyXZ;
    }

    public Integer getGxt() {
        return gxt;
    }

    public void setGxt(Integer gxt) {
        this.gxt = gxt;
    }

    public Integer getDxt() {
        return dxt;
    }

    public void setDxt(Integer dxt) {
        this.dxt = dxt;
    }

    public Integer getXuezhiYC() {
        return xuezhiYC;
    }

    public void setXuezhiYC(Integer xuezhiYC) {
        this.xuezhiYC = xuezhiYC;
    }

    public Integer getTfORnsgORjk() {
        return tfORnsgORjk;
    }

    public void setTfORnsgORjk(Integer tfORnsgORjk) {
        this.tfORnsgORjk = tfORnsgORjk;
    }

    public Integer getBanNianNeiShoushu() {
        return banNianNeiShoushu;
    }

    public void setBanNianNeiShoushu(Integer banNianNeiShoushu) {
        this.banNianNeiShoushu = banNianNeiShoushu;
    }

    public Integer getJiaKang() {
        return jiaKang;
    }

    public void setJiaKang(Integer jiaKang) {
        this.jiaKang = jiaKang;
    }

    public Integer getCangdaoWT() {
        return cangdaoWT;
    }

    public void setCangdaoWT(Integer cangdaoWT) {
        this.cangdaoWT = cangdaoWT;
    }

    public Integer getDanNangWT() {
        return danNangWT;
    }

    public void setDanNangWT(Integer danNangWT) {
        this.danNangWT = danNangWT;
    }

    public Integer getShengzangWT() {
        return shengzangWT;
    }

    public void setShengzangWT(Integer shengzangWT) {
        this.shengzangWT = shengzangWT;
    }

    public Integer getGuanjieWT() {
        return guanjieWT;
    }

    public void setGuanjieWT(Integer guanjieWT) {
        this.guanjieWT = guanjieWT;
    }

    public String getQitaWT() {
        return qitaWT;
    }

    public void setQitaWT(String qitaWT) {
        this.qitaWT = qitaWT;
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
        return "Jibing{" +
        ", id=" + id +
        ", 半年内手术=" + banNianNeiShoushu +
        ", gxt=" + gxt +
        ", dxt=" + dxt +
        ", xuezhiYC=" + xuezhiYC +
        ", tfORnsgORjk=" + tfORnsgORjk +
        ", jiaKang=" + jiaKang +
        ", cangdaoWT=" + cangdaoWT +
        ", danNangWT=" + danNangWT +
        ", shengzangWT=" + shengzangWT +
        ", guanjieWT=" + guanjieWT +
        ", qitaWT=" + qitaWT +
        ", userID=" + userID +
        "}";
    }
}
