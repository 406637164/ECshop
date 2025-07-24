package com.peter.ecshop.service;

import com.peter.ecshop.model.User;

public interface UserService {
    void registerUser(User user);
    String login(String username, String password);
    User findByUsername(String username);
}
