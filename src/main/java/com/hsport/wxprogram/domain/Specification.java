package com.hsport.wxprogram.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhb
 * @since 2020-01-04
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String specificationName;
    private String specificationBeizhu;
    private BigDecimal specificationPrice;
    private Integer productID;
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getSpecificationBeizhu() {
        return specificationBeizhu;
    }

    public void setSpecificationBeizhu(String specificationBeizhu) {
        this.specificationBeizhu = specificationBeizhu;
    }

    public BigDecimal getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(BigDecimal specificationPrice) {
        this.specificationPrice = specificationPrice;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
        ", id=" + id +
        ", specificationName=" + specificationName +
        ", specificationBeizhu=" + specificationBeizhu +
        ", specificationPrice=" + specificationPrice +
        ", productID=" + productID +
        "}";
    }
}
