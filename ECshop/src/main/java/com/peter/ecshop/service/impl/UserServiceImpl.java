package com.peter.ecshop.service.impl;

import com.peter.ecshop.dao.UserDao;
import com.peter.ecshop.model.User;
import com.peter.ecshop.service.UserService;
import com.peter.ecshop.util.JwtUtil;
import com.peter.ecshop.util.FuncUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private FuncUtil funcUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void registerUser(User user) {
        user.setUserId(funcUtil.generateRandomUserId());
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.insertUser(user);
    }

    @Override
    public String login(String username, String password) {
        User user = userDao.findByUsername(username);

        if (user != null && encoder.matches(password, user.getPassword())) {
            // 若密碼符合，產生 JWT Token
            return jwtUtil.generateToken(user.getUsername());
        }

        // 登入失敗
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
