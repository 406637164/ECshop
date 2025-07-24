package com.peter.ecshop.controller;

import com.peter.ecshop.model.User;
import com.peter.ecshop.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 用戶註冊
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "帳號已存在，請使用其他帳號");
            return ResponseEntity.badRequest().body(error);
        }
        userService.registerUser(user);
        Map<String, String> result = new HashMap<>();
        result.put("message", "註冊成功");
        return ResponseEntity.ok(result);
    }

    // 用戶登入
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user ) {
        System.out.println("登入帳號：" + user.getUsername());
        System.out.println("登入密碼：" + user.getPassword());

        String token = userService.login(user.getUsername(), user.getPassword());
        User foundUser = userService.findByUsername(user.getUsername());
        user.setUserId(foundUser.getUserId());
        user.setGroupId(foundUser.getGroupId());
        if (token != null) {

            return ResponseEntity.ok()

                    .body(Map.of(
                            "status", 200,
                            "message", "登入成功",
                            "userId", user.getUserId(),
                                "groupID",user.getGroupId(),
                            "token", token  // 直接把 token 放 body
                    ));
        } else {
            return ResponseEntity.status(401).body(
                    Map.of("status", 401, "message", "帳號或密碼錯誤")
            );
        }
    }

}
