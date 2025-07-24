package com.peter.ecshop.service.impl;

import com.peter.ecshop.dao.ProductDao;
import com.peter.ecshop.model.Product;
import com.peter.ecshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getProduct() {
        return productDao.getProduct();
    }
    @Override
    public Product getProductById(String productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public String createProduct(Product product) {
        return productDao.createProduct(product);
    }

    @Override
    public boolean updateProduct(String productId, Product product) {
        return productDao.updateProduct(productId, product);
    }

    @Override
    public boolean deleteProduct(String productId) {
        return productDao.deleteProduct(productId);
    }
}
