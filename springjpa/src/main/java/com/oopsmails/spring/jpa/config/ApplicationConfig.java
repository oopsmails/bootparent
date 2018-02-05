package com.oopsmails.spring.jpa.config;

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
