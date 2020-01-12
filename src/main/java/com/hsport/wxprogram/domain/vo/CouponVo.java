package com.hsport.wxprogram.domain.vo;

public class CouponVo {
    private Long userID;
    private Integer productID;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Long getUserID() {

        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
