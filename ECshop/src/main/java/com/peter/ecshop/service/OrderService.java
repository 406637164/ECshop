package com.peter.ecshop.service;

import com.peter.ecshop.model.Order;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.model.UserOrder;

import java.util.List;

public interface OrderService {
    List<UserOrder> getOrderById(String orderID);
    String insertOrder (Order order, List<Product> products);
}
