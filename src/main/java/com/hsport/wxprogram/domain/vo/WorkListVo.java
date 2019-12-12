package com.hsport.wxprogram.domain.vo;

public class WorkListVo {
    private String productName;
    private String  productserviceName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductserviceName() {
        return productserviceName;
    }

    public void setProductserviceName(String productserviceName) {
        this.productserviceName = productserviceName;
    }

    @Override
    public String toString() {
        return "workList{" +
                "productName='" + productName + '\'' +
                ", productserviceName='" + productserviceName + '\'' +
                '}';
    }
}
