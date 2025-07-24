package com.peter.ecshop.dao.impl;

import com.peter.ecshop.dao.OrderDao;
import com.peter.ecshop.model.Order;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.model.UserOrder;
import com.peter.ecshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<UserOrder> getOrderById(String userId) {
        String sql = "CALL GetUserOrders(:userId)";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(UserOrder.class));
    }
    @Override
    public String insertOrder(Order order, List<Product> products) {


        System.out.println(order.getOrderId());
        System.out.println(order.getMemberId());


        String insertOrderSql = "CALL insertOrder(:orderID, :memberID, :price, :payStatus)";
        Map<String, Object> orderParams = new HashMap<>();


        orderParams.put("orderID", order.getOrderId());
        orderParams.put("memberID", order.getMemberId());
        orderParams.put("price", order.getPrice());
        orderParams.put("payStatus", order.getPayStatus());

        namedParameterJdbcTemplate.update(insertOrderSql, orderParams);


        String insertDetailSql = "CALL insertOrderDetail(:orderID, :productID, :quantity, :standPrice, :itemPrice)";
        String updateStockSql = "CALL UpdateProductQuantityByOrderID(:orderID)";

        for (Product product : products) {
            Map<String, Object> detailParams = new HashMap<>();
            detailParams.put("orderID", order.getOrderId());
            detailParams.put("productID", product.getproductID());
            detailParams.put("quantity", product.getQuantity());
            detailParams.put("standPrice", product.getPrice());
            detailParams.put("itemPrice", product.getPrice() * product.getQuantity());

            namedParameterJdbcTemplate.update(insertDetailSql, detailParams);
        }


        Map<String, Object> stockParams = new HashMap<>();
        stockParams.put("orderID", order.getOrderId());
        namedParameterJdbcTemplate.update(updateStockSql, stockParams);

        return order.getOrderId();
    }
}
