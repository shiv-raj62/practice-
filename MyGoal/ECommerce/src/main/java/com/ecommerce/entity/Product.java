package com.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private boolean inStock;

    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> observers = new ArrayList<>();

    

    public Product(String name) {
        this.name = name;
        this.inStock = false;
    }


    public void addObserver(User user) {
        observers.add(user);
        user.setProduct(this);  
    }

  
    public void notifyObservers() {
        for (User observer : observers) {
        	
            observer.update(this.name);
        }
    }

   
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        notifyObservers();
    }
}
