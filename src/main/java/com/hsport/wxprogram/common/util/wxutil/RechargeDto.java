package com.hsport.wxprogram.common.util.wxutil;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/****************************************************
 *
 *  充值实体
 *
 * @author majker
 * @date: 2019/3/10
 * @version 1.0
 **************************************************/
@Data
public class RechargeDto {

    /**
     * 充值 支付类型
     * 0 微信 1 支付宝 2.公众号微信 3.微信小程序
     */
    private int payType;

    /**
     * 用户id
     */
    private Long userId;
    private Integer specificationID;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Integer productID;

    private Integer couponID;

    public Integer getCouponID() {
        return couponID;
    }

    public void setCouponID(Integer couponID) {
        this.couponID = couponID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    /**
     * 充值金额
     */

    private BigDecimal rechargeMoney;

    /**
     * 临时凭证code
     * 小程序支付调用wx.login();获取到登录临时凭证code
     */
    private String code;


    public Serializable getSpecificationID() {
        return specificationID;
    }
}
