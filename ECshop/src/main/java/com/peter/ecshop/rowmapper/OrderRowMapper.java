package com.peter.ecshop.rowmapper;

import com.peter.ecshop.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getString("OrderID"));
        order.setMemberId(rs.getString("MemberID"));
        order.setPrice(rs.getInt("Price"));
        order.setPayStatus(rs.getInt("PayStatus"));
        return order;
    }
}
