package com.oopsmails.spring.reactor.service;

import com.oopsmails.spring.reactor.domain.NotificationData;

public interface NotificationService {

    void initiateNotification(NotificationData notificationData) throws InterruptedException;

}
