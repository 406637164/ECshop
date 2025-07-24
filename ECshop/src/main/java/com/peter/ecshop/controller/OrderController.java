package com.peter.ecshop.controller;

import com.peter.ecshop.model.*;
import com.peter.ecshop.service.OrderService;
import com.peter.ecshop.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 建立訂單
    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequest orderRequest,
                              HttpServletRequest request) {

        System.out.println(orderRequest.getOrder().getMemberId());
        String orderId = orderService.insertOrder(orderRequest.getOrder(), orderRequest.getProducts());
        return "訂單建立成功，訂單編號：" + orderId;
    }
    @GetMapping("/order")
    public List<UserOrder> getOrdersByUserId(@RequestParam("userId") String userId) {
        return orderService.getOrderById(userId);
    }
}
