/**
 *
 */
package com.oopsmails.springboot.template.inmemorydb.hsql.service;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.UserRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.domain.UserCart;
import com.oopsmails.springboot.template.inmemorydb.hsql.mapper.UserCartRowMapper;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.User;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity;
import com.oopsmails.springboot.template.inmemorydb.hsql.mapper.UserRowMapper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private String insertOneSql;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users",
                new UserRowMapper());
    }

    @Transactional(readOnly = true)
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from users where userId=?",
                new Object[]{id}, new UserRowMapper());
    }

    @Transactional(readOnly = true)
    public List<User> findUserWithCartInfo(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM users u LEFT OUTER JOIN carts c ON u.userId = c.userId where u.userId=?",
                new Object[]{userId}, new UserRowMapper());
    }


    public List<UserCart> getAllUserCartMapping() {
//        return jdbcTemplate.query(
//                "SELECT u.userId, u.userName, c.ID as cartId, c.cartType FROM users u "
//                        + "LEFT OUTER JOIN carts c ON u.userId = c.userId WHERE c.userId IS NOT NULL",
//                new Object[]{}, new UserCartRowMapper());

        return jdbcTemplate.query(
                "SELECT u.userId, u.userName, c.ID as cartId, c.cartType FROM users u "
                        + "LEFT OUTER JOIN carts c ON u.userId = c.userId WHERE c.userId IS NOT NULL",
                new Object[]{}, new BeanPropertyRowMapper(UserCart.class));

    }

    public List<UserEntity> findAllUserWithCarts() {
        return userRepository.findDistinctByCartsNotNull();
//		return userRepository.findAll();
    }

    public List<UserEntity> findUserWithCarts(int UserId) {
        return userRepository.findDistinctByUserIdAndCartsIsNotNull(UserId);
    }

    public void createUsers(final List<User> users) {
        logger.debug("========================== test logger: createUsers ==========================");
        if (users.isEmpty()) {
            return;
        }

        List<SqlParameterSource> sqlParameterSourceList = new ArrayList<>();
        users.forEach((User user) ->
                sqlParameterSourceList.add(mapToSqlParameterSource(user))
        );

        namedParameterJdbcTemplate.batchUpdate(insertOneSql, sqlParameterSourceList.toArray(new SqlParameterSource[0]));
    }

    private SqlParameterSource mapToSqlParameterSource(User user) {
        MapSqlParameterSource result = new MapSqlParameterSource();

        result.addValue("userId", user.getUserId());
        result.addValue("userName", user.getUserName());
        result.addValue("userEmail", user.getUserEmail());
        result.addValue("address", user.getAddress());

        return result;

    }

    public User create(final User user) {
        jdbcTemplate.update(insertOneSql, (Object[]) new Object[]{
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getAddress()
        });

        return user;
    }
}
