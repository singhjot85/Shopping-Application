package com.oneclickshop.shop.controller;


import com.oneclickshop.shop.service.ProductService;
import com.oneclickshop.shop.model.*;
import com.oneclickshop.shop.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ProductController(ProductService productService, UserRepository userRepository, ProductRepository productRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product, @RequestParam String sellerId) {
        User seller = userRepository.findById(UUID.fromString(sellerId)).orElseThrow();
        return productService.addProduct(product, seller);
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestParam String productId, @RequestParam String buyerId, @RequestParam int orderQuantity) {

        Product product = productRepository.findById(Long.valueOf(productId)).orElseThrow();
        User buyer = userRepository.findById(UUID.fromString(buyerId)).orElseThrow();

        if (product.getQuantity() >= orderQuantity) {
            // Deduct the quantity of the product
            product.setQuantity(product.getQuantity() - orderQuantity);
            productRepository.save(product);

            // Create and save the order
            ProductOrder ord = new ProductOrder();
            ord.setBuyer(buyer);
            ord.setProduct(product);
            ord.setQuantity(orderQuantity);
            ord.setPurchaseDate(LocalDateTime.now());
            orderRepository.save(ord);

            // Notify the seller
            productService.notifySeller(product.getSeller(), buyer, product);

            return "Purchase successful";
        }
        return "Out of stock or insufficient quantity available";
    }

    @GetMapping("/orders/{buyerId}")
    public List<ProductOrder> getOrdersByBuyer(@PathVariable String buyerId) {
        User buyer = userRepository.findById(UUID.fromString(buyerId)).orElseThrow();
        return orderRepository.findByBuyer(buyer);
    }

}
