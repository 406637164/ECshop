package com.peter.ecshop.dao.impl;

import com.peter.ecshop.dao.ProductDao;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getProduct() {
        String sql = "CALL GetProduct(:productID)";

        Map<String, Object> params = new HashMap<>();
        params.put("productID", null); // 或者 null，視你的 stored procedure 處理方式而定

        return namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(String productID) {
        String sql = "CALL GetProduct(:productID)";

        Map<String, Object> params = new HashMap<>();
        params.put("productID", productID);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());

        return (productList != null && !productList.isEmpty()) ? productList.get(0) : null;
    }

    @Override
    public String createProduct(Product product) {
        String sql = "CALL create_product(:p_productName, :p_price, :p_quantity)";

        Map<String, Object> params = new HashMap<>();
        params.put("p_productName", product.getProductName());
        params.put("p_price", product.getPrice());
        params.put("p_quantity", product.getQuantity());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
        return productList.isEmpty() ? null : productList.get(0).getProductName();
    }

    @Override
    public boolean updateProduct(String productID, Product product) {
        String sql = "CALL update_product(:p_productID, :p_productName, :p_price, :p_quantity)";

        Map<String, Object> params = new HashMap<>();
        params.put("p_productID", productID);
        params.put("p_productName", product.getProductName());
        params.put("p_price", product.getPrice());
        params.put("p_quantity", product.getQuantity());

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        System.out.println(sql);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteProduct(String productID) {
        String sql = "CALL delete_product(:p_productID)";

        Map<String, Object> params = new HashMap<>();
        params.put("p_productID", productID);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }
}
