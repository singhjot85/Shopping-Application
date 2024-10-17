package com.oneclickshop.shop.service;

import com.oneclickshop.shop.model.*;
import com.oneclickshop.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

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

    public void createOrder(User buyer, Product product, int quantity) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setBuyer(buyer);
        productOrder.setProduct(product);
        productOrder.setQuantity(quantity);
        productOrder.setPurchaseDate(LocalDateTime.now());

        orderRepository.save(productOrder); // Use orderRepository to save the productOrder
    }

}
