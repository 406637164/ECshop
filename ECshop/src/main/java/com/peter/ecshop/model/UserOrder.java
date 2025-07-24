package com.peter.ecshop.model;

public class UserOrder {
    private String orderId;
    private String memberId;
    private Integer price;
    private Integer payStatus; // 0: 未付款, 1: 已付款
    private String quantity; // 0: 未付款, 1: 已付款
    private String productName; // 0: 未付款, 1: 已付款

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // 無參數建構子
    public UserOrder() {}

    // Getter & Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}
