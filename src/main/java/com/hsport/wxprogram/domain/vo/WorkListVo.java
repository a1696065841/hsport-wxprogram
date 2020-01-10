package com.hsport.wxprogram.domain.vo;

public class WorkListVo {
    private String productName;
    private String  productserviceName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private Integer serviceType;
    private boolean isOnWork;
    public WorkListVo(){}
    public WorkListVo(String name, Integer serviceType, boolean isOnWork) {
        this.name = name;
        this.serviceType = serviceType;
        this.isOnWork = isOnWork;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isOnWork() {
        return isOnWork;
    }

    public void setOnWork(boolean onWork) {
        isOnWork = onWork;
    }

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
