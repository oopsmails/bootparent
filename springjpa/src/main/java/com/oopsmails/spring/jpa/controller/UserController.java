package com.oopsmails.spring.jpa.controller;

import com.oopsmails.spring.jpa.entity.User;
import com.oopsmails.spring.jpa.repository.UserRepository;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(name = "get all users", method = RequestMethod.GET, value = "/users")
    List<User> findAllUsers() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        slf4jLogger.info("==================== Testing logging,  {}", sort);
        return userRepository.findAll(sort);
    }
}
