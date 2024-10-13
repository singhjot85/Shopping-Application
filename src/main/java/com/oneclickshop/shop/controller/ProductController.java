//package com.oneclickshop.shop.controller;
//
//import com.oneclickshop.shop.model.Product;
//import com.oneclickshop.shop.model.User;
//import com.oneclickshop.shop.repository.ProductRepository;
//import com.oneclickshop.shop.repository.UserRepository;
//import com.oneclickshop.shop.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    @Autowired
//    private final ProductService productService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping("/add")
//    public Product addProduct(@RequestBody Product product, @RequestParam Long sellerId) {
//        User seller = userRepository.findById(sellerId).orElseThrow();
//        return productService.addProduct(product, seller);
//    }
//
//    @PostMapping("/buy")
//    public String buyProduct(@RequestParam Long productId, @RequestParam Long buyerId) {
//        Product product = productRepository.findById(productId).orElseThrow();
//        User buyer = userRepository.findById(buyerId).orElseThrow();
//
//        if (product.getQuantity() > 0) {
//            product.setQuantity(product.getQuantity() - 1);
//            productRepository.save(product);
//            productService.notifySeller(product.getSeller(), buyer, product);
//            return "Purchase successful";
//        }
//        return "Out of stock";
//    }
//}
