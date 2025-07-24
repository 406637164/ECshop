package com.peter.ecshop.dao;

import com.peter.ecshop.model.User;

public interface UserDao {
    void insertUser(User user);
    User findByUsername(String username);  // 查詢帳號用來驗證是否重複
}
