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

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    /**
     * 从业时间
     */
    private String employmentTime;

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
        return employmentTime;
    }

    public void setEmploymentTime(String employmentTime) {
        this.employmentTime = employmentTime;
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
        ", EmploymentTime=" + employmentTime +
        ", gymID=" + gymID +
        ", coachPic=" + coachPic +
        "}";
    }
}
