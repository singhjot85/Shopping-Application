package com.oneclickshop.shop.service;

import com.oneclickshop.shop.model.Product;
import com.oneclickshop.shop.model.User;
import com.oneclickshop.shop.repository.ProductRepository;
import com.oneclickshop.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "products", key = "#seller.id")
    public List<Product> getProductsBySeller(User seller) {
        return productRepository.findBySeller(seller);
    }

    public Product addProduct(Product product, User seller) {
        product.setSeller(seller);
        return productRepository.save(product);
    }

    public void notifySeller(User seller, User buyer, Product product) {
        // Implement notification logic here
        System.out.println("Notification: " + buyer.getUsername() + " bought " + product.getName() + " from " + seller.getUsername());
    }
}
