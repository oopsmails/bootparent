package com.oopsmails.springboot.template.inmemorydb.hsql.mapper;

import com.oopsmails.springboot.template.inmemorydb.hsql.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setUserName(rs.getString("userName"));
        user.setUserEmail(rs.getString("userEmail"));
        user.setAddress(rs.getString("address"));
        return user;
    }

}
