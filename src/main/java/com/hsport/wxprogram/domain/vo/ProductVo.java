package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.domain.Productservice;

import java.util.List;

public class ProductVo {
    private Product product;
    private List<Productservice> productservices;
    private  List<Gym> gyms;

    public List<Gym> getGyms() {
        return gyms;
    }

    public void setGyms(List<Gym> gyms) {
        this.gyms = gyms;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Productservice> getProductservices() {

        return productservices;
    }

    public void setProductservices(List<Productservice> productservices) {
        this.productservices = productservices;
    }
}
