package com.ecommerce.main.observer;

import com.ecommerce.main.server.IObserver;

public class User implements IObserver {
	private String name;

	public User(String name) {
		this.name = name;
	}

	@Override
	public void update(String productName) {
		System.out.println("Hello " + name + ", the product " + productName + " is now back in stock!");
	}
}
