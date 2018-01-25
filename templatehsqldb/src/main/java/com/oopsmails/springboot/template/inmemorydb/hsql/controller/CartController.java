package com.oopsmails.springboot.template.inmemorydb.hsql.controller;

import com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity;
import com.oopsmails.springboot.template.inmemorydb.hsql.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(name = "get all carts", method = RequestMethod.GET, value = "/carts")
    List<CartEntity> findAllCarts() {
        return cartService.findAll();
    }

}
