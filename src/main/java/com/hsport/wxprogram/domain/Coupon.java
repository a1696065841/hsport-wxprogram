package com.hsport.wxprogram.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2020-01-11
 */
@TableName("t_coupon")
public class Coupon extends Model<Coupon> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 优惠卷名字
     */
    private String name;
    /**
     * 优惠卷类型
     */
    private Integer couponType;
    /**
     * 优惠金额
     */
    private BigDecimal couponMoney;
    private String imgUrl;
    private String startTime;
    private String endTime;
    private BigDecimal fullmoney;
    private Integer productID;
    private Boolean allOrSome;

    @TableField(exist = false)
    private Integer couponUserID;

    public Integer getCouponUserID() {
        return couponUserID;
    }

    public void setCouponUserID(Integer couponUserID) {
        this.couponUserID = couponUserID;
    }

    public Boolean getAllOrSome() {
        return allOrSome;
    }

    public void setAllOrSome(Boolean allOrSome) {
        this.allOrSome = allOrSome;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
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

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFullmoney() {
        return fullmoney;
    }

    public void setFullmoney(BigDecimal fullmoney) {
        this.fullmoney = fullmoney;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Coupon{" +
        ", id=" + id +
        ", name=" + name +
        ", couponType=" + couponType +
        ", couponMoney=" + couponMoney +
        ", imgUrl=" + imgUrl +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", fullmoney=" + fullmoney +
        "}";
    }
}
