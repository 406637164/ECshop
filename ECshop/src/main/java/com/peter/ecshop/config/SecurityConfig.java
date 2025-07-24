package com.peter.ecshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

@Autowired
private JwtFilter jwtAuthenticationFilter;
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/users/**").permitAll()   // 登入、註冊放行
                     .requestMatchers(HttpMethod.POST,"/products/**").authenticated()  // 訂單需要認證
                     .requestMatchers(HttpMethod.PUT,"/products/**").authenticated()  // 訂單需要認證
                     .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()  // 訂單需要認證

                            .anyRequest().permitAll()   // 其他路徑放行
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 無狀態 session
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build(); // ❗️記得要加上 return！
}
}
