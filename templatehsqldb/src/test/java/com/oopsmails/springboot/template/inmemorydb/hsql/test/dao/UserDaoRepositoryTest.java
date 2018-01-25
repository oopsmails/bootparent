package com.oopsmails.springboot.template.inmemorydb.hsql.test.dao;

import static org.junit.Assert.assertEquals;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.UserRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoRepositoryTest {

    @Autowired
    private UserRepository userDaoRepository;

    @Test
    public void testUserDaoFindByUserName() {
        List<UserEntity> users = userDaoRepository.findByUserName("Dinesh");
        assertEquals(1, users.size());
    }
}
