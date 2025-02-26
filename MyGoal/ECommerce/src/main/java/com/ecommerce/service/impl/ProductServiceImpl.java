package com.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

   
    @Transactional
    public Product addProduct(String productName) {
        Product product = new Product(productName);
        return productRepository.save(product);
    }

  
    @Transactional
    public void addObserver(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        product.addObserver(user);
        productRepository.save(product);
    }

   
    @Transactional
    public void changeStockStatus(Long productId, boolean inStock) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setInStock(inStock);
        productRepository.save(product);
    }
}
