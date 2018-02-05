package com.oopsmails.springboot.template.inmemorydb.hsql.service;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.CartEntityRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Transactional(readOnly = true)
    public List<CartEntity> findAll() {
        return cartEntityRepository.findAll();
    }


}
