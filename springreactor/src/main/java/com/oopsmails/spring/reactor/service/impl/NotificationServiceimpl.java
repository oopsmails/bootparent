package com.oopsmails.spring.reactor.service.impl;

import com.oopsmails.spring.reactor.domain.NotificationData;
import com.oopsmails.spring.reactor.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceimpl implements NotificationService {

    @Override
    public void initiateNotification(NotificationData notificationData) throws InterruptedException {
        System.out.println("Thread: " + Thread.currentThread().getId() + " -- Notification service started for Notification ID: " + notificationData.getId());

        Thread.sleep(5000);

        System.out.println("Thread: " + Thread.currentThread().getId() + " -- Notification service ended for Notification ID: " + notificationData.getId());
    }

}
