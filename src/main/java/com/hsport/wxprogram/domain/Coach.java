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
@TableName("t_coach")
public class Coach extends Model<Coach> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String coachName;
    private Integer coachAge;
    /**
     * 从业时间
     */
    private String EmploymentTime;
    private Integer CertificateID;
    /**
     * 健身房id
     */
    private Integer gymID;
    /**
     * 照片
     */
    private String coachPic;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getCoachAge() {
        return coachAge;
    }

    public void setCoachAge(Integer coachAge) {
        this.coachAge = coachAge;
    }

    public String getEmploymentTime() {
        return EmploymentTime;
    }

    public void setEmploymentTime(String EmploymentTime) {
        this.EmploymentTime = EmploymentTime;
    }

    public Integer getCertificateID() {
        return CertificateID;
    }

    public void setCertificateID(Integer CertificateID) {
        this.CertificateID = CertificateID;
    }

    public Integer getGymID() {
        return gymID;
    }

    public void setGymID(Integer gymID) {
        this.gymID = gymID;
    }

    public String getCoachPic() {
        return coachPic;
    }

    public void setCoachPic(String coachPic) {
        this.coachPic = coachPic;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Coach{" +
        ", id=" + id +
        ", coachName=" + coachName +
        ", coachAge=" + coachAge +
        ", EmploymentTime=" + EmploymentTime +
        ", CertificateID=" + CertificateID +
        ", gymID=" + gymID +
        ", coachPic=" + coachPic +
        "}";
    }
}
