package com.ecommerce.main.observer;

import com.ecommerce.main.notification.NotificationService;
import com.ecommerce.main.server.IObserver;


public class ProductObserverImpl implements IObserver {
	private String userName;
	private NotificationService notificationService;
	public ProductObserverImpl()
	{
		
	}

	public ProductObserverImpl(String userName, NotificationService notificationService) {
		this.userName = userName;
		this.notificationService = notificationService;
	}

	@Override
	public void update(String productName) {
		String message = "Dear " + userName + ", the product " + productName + " is back in stock! ";
	///	System.out.println("massage : "+message);
		notificationService.sendNotification(message);
		
	}

	public String getUserName() {
		return userName;
	}
}
