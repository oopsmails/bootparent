package com.oopsmails.springboot.template.inmemorydb.hsql.test.dao;

import static org.junit.Assert.assertEquals;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.CartRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCartDaoFindByUserId() {
        List<CartEntity> carts = cartRepository.findByUserId(1000);
        assertEquals(2, carts.size());
    }
}
