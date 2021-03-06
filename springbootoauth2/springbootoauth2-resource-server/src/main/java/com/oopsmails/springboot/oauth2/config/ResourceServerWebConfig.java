package com.oopsmails.springboot.oauth2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"com.oopsmails.springboot.oauth2.web.controller"})
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    //
}
