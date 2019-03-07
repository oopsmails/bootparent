package com.oopsmails.springboot.oauth2.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * https://stackoverflow.com/questions/38843015/spring-boot-springbootservletinitializer-is-deprecated
 *
 *org.springframework.boot.web.support.SpringBootServletInitializer

 For SpringBoot 2.0

 org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 */

@SpringBootApplication
public class AuthorizationServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
