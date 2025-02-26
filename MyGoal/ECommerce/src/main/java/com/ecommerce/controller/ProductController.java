package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entity.Product;
import com.ecommerce.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

   
    @PostMapping("/add")
    public Product addProduct(@RequestParam String productName) {
        return productService.addProduct(productName);
    }

    
    @PostMapping("/addObserver")
    public String addObserver(@RequestParam Long productId, @RequestParam Long userId) {
        productService.addObserver(productId, userId);
        return "User added as observer!";
    }

   
    @PostMapping("/changeStock")
    public String changeStockStatus(@RequestParam Long productId, @RequestParam boolean inStock) {
        productService.changeStockStatus(productId, inStock);
        return "Stock status updated and users notified!";
    }
}
