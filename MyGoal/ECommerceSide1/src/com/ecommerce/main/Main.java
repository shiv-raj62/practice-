package com.ecommerce.main;

import com.ecommerce.main.model.Product;
import com.ecommerce.main.observer.User;
import com.ecommerce.main.notification.NotificationService;
import com.ecommerce.main.notification.SmsNotification;

public class Main {
    public static void main(String[] args) {
        Product laptop = new Product("P001", "Laptop", 999.99);

        NotificationService notificationService = new SmsNotification();

        User alice = new User("Alice");
        
        laptop.addObserver(alice);
        
        System.out.println("Changing stock status of the product to in stock...");
        laptop.setInStock(true);  

        System.out.println("Changing stock status of the product to out of stock...");
        laptop.setInStock(false);

        System.out.println("Changing stock status of the product to in stock...");
        laptop.setInStock(true);  
    }
}
