package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lhb
 * @since 2019-12-22
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@TableName("t_lxxx")
public class Lxxx extends Model<Lxxx> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;
    private String telphone;
    private String weixinNum;
    private String birthday;
    private String occupation;
    private String nation;
    /**
     * 所在地
     */
    private String atAddress;
    private String postalAddress;
    private String homeAddress;
    private String homePhone;
    /**
     * 紧急联系人姓名
     */
    private String ecName;
    /**
     * 紧急联系人电话
     */
    private String ecPhone;
    private Long userID;
    private String specialNeeds;
    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

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

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(String weixinNum) {
        this.weixinNum = weixinNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAtAddress() {
        return atAddress;
    }

    public void setAtAddress(String atAddress) {
        this.atAddress = atAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getEcPhone() {
        return ecPhone;
    }

    public void setEcPhone(String ecPhone) {
        this.ecPhone = ecPhone;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Lxxx{" +
                ", id=" + id +
                ", name=" + name +
                ", telphone=" + telphone +
                ", weixinNum=" + weixinNum +
                ", birthday=" + birthday +
                ", occupation=" + occupation +
                ", nation=" + nation +
                ", atAddress=" + atAddress +
                ", postalAddress=" + postalAddress +
                ", homeAddress=" + homeAddress +
                ", homePhone=" + homePhone +
                ", ecName=" + ecName +
                ", ecPhone=" + ecPhone +
                ", userID=" + userID +
                ", specialNeeds=" + specialNeeds +
                "}";
    }
}
