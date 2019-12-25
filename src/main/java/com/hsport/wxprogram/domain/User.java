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
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String phone;
    private Integer coachID;
    private String password;

    /**
     * 创建时间
     */
    private String genTime;
    /**
     * 登陆时间
     */
    private String loginTime;
    /**
     * 登陆次数
     */
    private Integer loginCount;

    /**
     * userType
     */
    private Integer userType;
    private Integer bodyID;
    /**
     * 所在地
     */
    /**
     * 通讯地址
     */


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getCoachID() {
        return coachID;
    }

    public void setCoachID(Integer coachID) {
        this.coachID = coachID;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getBodyID() {
        return bodyID;
    }

    public void setBodyID(Integer bodyID) {
        this.bodyID = bodyID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", phone=" + phone +
                ", coachID=" + coachID +
                ", genTime=" + genTime +
                ", loginTime=" + loginTime +
                ", loginCount=" + loginCount +
                ", userType=" + userType +
                ", bodyID=" + bodyID +
                "}";
    }
}
