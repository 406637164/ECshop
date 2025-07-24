package com.peter.ecshop.service.impl;


import com.peter.ecshop.dao.OrderDao;

import com.peter.ecshop.model.Order;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.model.UserOrder;
import com.peter.ecshop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao; // 這裡改對名稱


    @Override
    public List<UserOrder> getOrderById(String orderID) {
        return orderDao.getOrderById(orderID );
    }

    @Override
    @Transactional
    public String insertOrder(Order order, List<Product> products) {

        return orderDao.insertOrder(order, products
        );
    }
}