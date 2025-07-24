package com.peter.ecshop.dao.impl;

import com.peter.ecshop.dao.UserDao;
import com.peter.ecshop.model.User;
import com.peter.ecshop.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void insertUser(User user) {
        String sql = "CALL insert_user(:p_userId, :p_username, :p_password, :p_fullName, :p_email,:p_groupId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("p_userId", user.getUserId());
        params.addValue("p_username", user.getUsername());
        params.addValue("p_password", user.getPassword());
        params.addValue("p_fullName", user.getFullName());
        params.addValue("p_email", user.getEmail());
        params.addValue("p_groupId", user.getGroupId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "CALL find_user_by_username(:username)";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        List<User> users = namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());

        return users.isEmpty() ? null : users.get(0);
    }
}
