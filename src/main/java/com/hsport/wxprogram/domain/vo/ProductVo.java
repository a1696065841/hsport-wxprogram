package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Details;
import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.domain.Productservice;

import java.util.List;

public class ProductVo {
    private Product product;
    private List<Productservice> productservices;
    private  List<Integer> gyms;
    private  List<Details> details;

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public List<Integer> getGyms() {
        return gyms;
    }

    public void setGyms(List<Integer> gyms) {
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
