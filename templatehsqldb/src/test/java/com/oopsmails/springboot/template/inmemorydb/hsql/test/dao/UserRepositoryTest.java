package com.oopsmails.springboot.template.inmemorydb.hsql.test.dao;

import static org.junit.Assert.assertEquals;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.CartEntityRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.dao.UserRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private UserRepository userDaoRepository;

    @Test
    public void testUserRepositoryFindByCartsNotNull() {
        List<CartEntity> cartEntityList = cartEntityRepository.findAll();
        assertEquals(3, cartEntityList.size());

        List<UserEntity> userEntityList = userDaoRepository.findAll();
        assertEquals(3, userEntityList.size());

        List<UserEntity> users = userRepository.findDistinctByCartsNotNull();
        assertEquals(2, users.size());
    }
}
