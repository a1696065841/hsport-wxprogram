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
 * @since 2019-11-29
 */
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String wx;
    private Integer phone;
    private Integer age;
    private Integer sex;
    private String position;
    private String address;
    private Integer jjlxrdh;
    private String jjlxrxm;
    private Integer coachID;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    private Integer roleID;
    /**
     * userType
     */
    private Integer userType;
    private Integer bodyID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getJjlxrdh() {
        return jjlxrdh;
    }

    public void setJjlxrdh(Integer jjlxrdh) {
        this.jjlxrdh = jjlxrdh;
    }

    public String getJjlxrxm() {
        return jjlxrxm;
    }

    public void setJjlxrxm(String jjlxrxm) {
        this.jjlxrxm = jjlxrxm;
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

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
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
        ", name=" + name +
        ", wx=" + wx +
        ", phone=" + phone +
        ", age=" + age +
        ", sex=" + sex +
        ", position=" + position +
        ", address=" + address +
        ", jjlxrdh=" + jjlxrdh +
        ", jjlxrxm=" + jjlxrxm +
        ", coachID=" + coachID +
        ", genTime=" + genTime +
        ", loginTime=" + loginTime +
        ", loginCount=" + loginCount +
        ", roleID=" + roleID +
        ", userType=" + userType +
        ", bodyID=" + bodyID +
        "}";
    }
}
