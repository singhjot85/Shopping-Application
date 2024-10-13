package com.oneclickshop.shop.repository;

import com.oneclickshop.shop.model.Product;
import com.oneclickshop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(User seller);
}

