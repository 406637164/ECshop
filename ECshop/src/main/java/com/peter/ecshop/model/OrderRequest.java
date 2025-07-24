package com.peter.ecshop.model;

import com.peter.ecshop.model.Order ;
import com.peter.ecshop.model.Product;

import java.util.List;

public class OrderRequest {
    private Order order;
    private List<Product> products;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
