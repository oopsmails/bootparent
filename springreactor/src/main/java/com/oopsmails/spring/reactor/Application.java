package com.oopsmails.spring.reactor;

import com.oopsmails.spring.reactor.consumer.NotificationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(ReactorConfig.class)
public class Application implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private NotificationConsumer notificationConsumer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        eventBus.on($("notificationConsumer"), notificationConsumer);
    }

}
