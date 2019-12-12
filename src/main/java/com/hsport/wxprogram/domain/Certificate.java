package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2019-11-21
 */
@TableName("t_certificate")
public class Certificate extends Model<Certificate> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;
    /**
     * 证书类型
     */
    private String CertificateName;
    /**
     * 证书类型
     */
    @TableField("cf_type")
    private String cfType;
    @TableField("cf_date")
    private String cfDate;
    @TableField("coach_id")
    private Integer coachId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificateName() {
        return CertificateName;
    }

    public void setCertificateName(String CertificateName) {
        this.CertificateName = CertificateName;
    }

    public String getCfType() {
        return cfType;
    }

    public void setCfType(String cfType) {
        this.cfType = cfType;
    }

    public String getCfDate() {
        return cfDate;
    }

    public void setCfDate(String cfDate) {
        this.cfDate = cfDate;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Certificate{" +
        ", id=" + id +
        ", CertificateName=" + CertificateName +
        ", cfType=" + cfType +
        ", cfDate=" + cfDate +
        ", coachId=" + coachId +
        "}";
    }
}
