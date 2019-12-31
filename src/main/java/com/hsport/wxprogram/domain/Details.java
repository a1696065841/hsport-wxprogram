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
 * @since 2019-12-30
 */
@TableName("t_details")
public class Details extends Model<Details> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detailsID", type = IdType.AUTO)
    private Integer detailsID;
    private Integer productID;
    private String imgUrl;
    private String detailsName;
    private String detailsDesc;


    public Integer getDetailsID() {
        return detailsID;
    }

    public void setDetailsID(Integer detailsID) {
        this.detailsID = detailsID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public String getDetailsDesc() {
        return detailsDesc;
    }

    public void setDetailsDesc(String detailsDesc) {
        this.detailsDesc = detailsDesc;
    }

    @Override
    protected Serializable pkVal() {
        return this.detailsID;
    }

    @Override
    public String toString() {
        return "Details{" +
        ", detailsID=" + detailsID +
        ", productID=" + productID +
        ", imgUrl=" + imgUrl +
        ", detailsName=" + detailsName +
        ", detailsDesc=" + detailsDesc +
        "}";
    }
}
