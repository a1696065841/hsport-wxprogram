package com.hsport.wxprogram.domain.vo;

public class OrderVo {
    private Long CoachID;
    private Long UserID;
    private String orderID;

    public Long getCoachID() {
        return CoachID;
    }

    public void setCoachID(Long coachID) {
        CoachID = coachID;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
