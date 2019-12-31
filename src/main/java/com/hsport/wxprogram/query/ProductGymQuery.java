package com.hsport.wxprogram.query;


/**
 *
 * @author lhb
 * @since 2019-12-30
 */
import com.hsport.wxprogram.common.util.BaseQuery;


public class ProductGymQuery extends BaseQuery{
    private Integer productID;
    private Integer gymID;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getGymID() {
        return gymID;
    }

    public void setGymID(Integer gymID) {
        this.gymID = gymID;
    }
}