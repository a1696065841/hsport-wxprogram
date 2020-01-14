package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2020-01-13
 */
@TableName("t_code")
public class Code extends Model<Code> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer couponID;
    private Boolean isUsed;
    private Long userID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCouponID() {
        return couponID;
    }

    public void setCouponID(Integer couponID) {
        this.couponID = couponID;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Code{" +
        ", codeID=" + id +
        ", couponID=" + couponID +
        ", isUsed=" + isUsed +
        ", userID=" + userID +
        "}";
    }
}
