package com.oopsmails.springboot.template.inmemorydb.hsql.mapper;

import com.oopsmails.springboot.template.inmemorydb.hsql.domain.UserCart;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserCartRowMapper implements RowMapper<UserCart> {
    @Override
    public UserCart mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCart userCart = new UserCart();
        userCart.setUserId(rs.getInt("userId"));
        userCart.setUserName(rs.getString("userName"));
        userCart.setCartId(rs.getInt("cartId"));
        userCart.setCartType(rs.getString("cartType"));
        return userCart;
    }
}
