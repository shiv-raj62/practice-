package com.ecommerce.main.model;



import java.util.ArrayList;
import java.util.List;

import com.ecommerce.main.server.IObserver;

public class Product {

	private String productId;
	private String name;
	private double price;
	private boolean isInStock;
	private List<IObserver> observers;

	public Product(String productId, String name, double price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.isInStock = false;
		observers = new ArrayList<>();
	}

	// Getter methods for product details
	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean isInStock() {
		return isInStock;
	}

	public void addObserver(IObserver observer) {
		observers.add(observer);
	}

	private void notifyObservers() {
		for (IObserver observer : observers) {
			observer.update(name); // Send both product name and price
		}
	}

	public void setInStock(boolean inStock) {
		this.isInStock = inStock;
		if (inStock) {
			notifyObservers();
		}
	}
}
