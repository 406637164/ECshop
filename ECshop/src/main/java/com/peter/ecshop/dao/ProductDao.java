package com.peter.ecshop.dao;


import com.peter.ecshop.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProduct();
    Product getProductById(String productId);
    String createProduct(Product product);
    boolean updateProduct(String productId, Product product);
    boolean deleteProduct(String productId);
}
