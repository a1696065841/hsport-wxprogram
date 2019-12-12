package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("t_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String productName;
    private String pirce;
    /**
     * 产品描述
     */
    @TableField("pro_desc")
    private String proDesc;
    private Integer sysuserID;
    @TableField("productType")
    private Integer productType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPirce() {
        return pirce;
    }

    public void setPirce(String pirce) {
        this.pirce = pirce;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public Integer getSysuserID() {
        return sysuserID;
    }

    public void setSysuserID(Integer sysuserID) {
        this.sysuserID = sysuserID;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer proType) {
        this.productType = proType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Product{" +
        ", id=" + id +
        ", productName=" + productName +
        ", pirce=" + pirce +
        ", proDesc=" + proDesc +
        ", sysuserID=" + sysuserID +
        ", productType=" + productType +
        "}";
    }
}
