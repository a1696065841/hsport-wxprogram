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
@TableName("t_sljk")
public class Sljk extends Model<Sljk> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 排便是否正常
     */
    private Integer pbSFzc;
    /**
     * 疾病历史
     */
    private Integer sfYjbLS;
    /**
     * 是否服用药物
     */
    private Integer sfFYyaowu;
    private Integer userID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPbSFzc() {
        return pbSFzc;
    }

    public void setPbSFzc(Integer pbSFzc) {
        this.pbSFzc = pbSFzc;
    }

    public Integer getSfYjbLS() {
        return sfYjbLS;
    }

    public void setSfYjbLS(Integer sfYjbLS) {
        this.sfYjbLS = sfYjbLS;
    }

    public Integer getSfFYyaowu() {
        return sfFYyaowu;
    }

    public void setSfFYyaowu(Integer sfFYyaowu) {
        this.sfFYyaowu = sfFYyaowu;
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
        return "Sljk{" +
        ", id=" + id +
        ", pbSFzc=" + pbSFzc +
        ", jbLS=" + sfYjbLS +
        ", sfFYyaowu=" + sfFYyaowu +
        ", userID=" + userID +
        "}";
    }
}
