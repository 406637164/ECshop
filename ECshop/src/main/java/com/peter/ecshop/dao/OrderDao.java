package com.peter.ecshop.dao;


import com.peter.ecshop.model.Order;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.model.UserOrder;

import java.util.List;

public interface OrderDao {
    List<UserOrder> getOrderById(String orderID);
    String insertOrder(Order order, List<Product> products); // 修改這裡
}
