package com.oopsmails.springboot.template.inmemorydb.hsql.controller;

import com.oopsmails.springboot.template.inmemorydb.hsql.domain.UserCart;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.User;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity;
import com.oopsmails.springboot.template.inmemorydb.hsql.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(name = "create user", method = RequestMethod.POST, value = "/user")
    User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(name = "create multiple users", method = RequestMethod.POST, value = "/users")
    void createUsers(@RequestBody List<User> users) {
        userService.createUsers(users);
    }

    @RequestMapping(name = "get all user", method = RequestMethod.GET, value = "/users")
    List<User> findAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(name = "get all user cart mapping", method = RequestMethod.GET, value = "/usercartmap")
    List<UserCart> findAllUserCartMapping() {
        return userService.getAllUserCartMapping();
    }

    @RequestMapping(name = "get user with cart info", method = RequestMethod.GET, value = "/user/cartinfo/{userId}")
    List<User> findUserWithCartInfo(@PathVariable("userId") int userId) {
        return userService.findUserWithCartInfo(userId);
    }

    @RequestMapping(name = "get all user with carts", method = RequestMethod.GET, value = "/user/cart")
    List<UserEntity> findUserWithCart() {
        return userService.findAllUserWithCarts();
    }

    @RequestMapping(name = "get user with carts", method = RequestMethod.GET, value = "/user/cart/{userId}")
    List<UserEntity> findUserWithCartByUserId(@PathVariable("userId") int userId) {
        return userService.findUserWithCarts(userId);
    }
}
