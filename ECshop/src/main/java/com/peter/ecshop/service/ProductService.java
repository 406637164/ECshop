package com.peter.ecshop.service;

import com.peter.ecshop.model.Product;

import java.util.List;

public interface ProductService
{
    Product getProductById(String productId);
    List<Product> getProduct();
    String createProduct(Product product);
    boolean updateProduct(String productId, Product product);
    boolean deleteProduct(String productId);
}
