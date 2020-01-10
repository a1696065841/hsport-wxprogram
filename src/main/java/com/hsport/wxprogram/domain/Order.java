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
@TableName("t_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    private String id;
    private Long userID;
    private Integer consigneeID;
    private Integer regionID;
    private Integer isDistribution;
    private Integer specificationID;
    private Integer couponID;
    /**
     * 购买时间
     */
    private String startDate;
    private Integer productID;
    private Double totalPrice;
    private Integer orderType;
    /**
     * 订单完成时间
     */
    private String endDate;

    public Integer getCouponID() {
        return couponID;
    }

    public void setCouponID(Integer couponID) {
        this.couponID = couponID;
    }

    public Integer getSpecificationID() {
        return specificationID;
    }

    public void setSpecificationID(Integer specificationID) {
        this.specificationID = specificationID;
    }

    public Integer getIsDistribution() {
        return isDistribution;
    }

    public void setIsDistribution(Integer isDistribution) {
        this.isDistribution = isDistribution;
    }

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public Integer getConsigneeID() {
        return consigneeID;
    }

    public void setConsigneeID(Integer consigneeID) {
        this.consigneeID = consigneeID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        ", id=" + id +
        ", userID=" + userID +
        ", stratDate=" + startDate +
        ", productID=" + productID +
        ", totalPrice=" + totalPrice +
        ", orderType=" + orderType +
        ", endDate=" + endDate +
        "}";
    }
}
