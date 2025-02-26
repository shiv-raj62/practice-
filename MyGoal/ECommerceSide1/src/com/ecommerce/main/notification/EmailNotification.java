package com.ecommerce.main.notification;


public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email: " + message);
    }
}
