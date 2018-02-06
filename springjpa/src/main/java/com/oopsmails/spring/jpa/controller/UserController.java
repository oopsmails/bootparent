package com.oopsmails.spring.jpa.controller;

import com.oopsmails.spring.jpa.entity.User;
import com.oopsmails.spring.jpa.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(name = "get all users", method = RequestMethod.GET, value = "/users")
    List<User> findAllUsers() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return userRepository.findAll(sort);
    }
}
