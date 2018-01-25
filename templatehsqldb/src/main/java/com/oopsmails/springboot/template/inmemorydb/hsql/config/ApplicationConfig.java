package com.oopsmails.springboot.template.inmemorydb.hsql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean(name = "insertOneSql")
    public String insertOneSql() {
        return "INSERT INTO users ( userId, userName, userEmail, address ) "
                + "VALUES (:userId, :userName, :userEmail, :address ) ";
    }

}
