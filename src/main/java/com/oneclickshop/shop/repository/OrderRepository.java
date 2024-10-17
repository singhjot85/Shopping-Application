package com.oneclickshop.shop.repository;

import java.util.List;
import java.util.UUID;

import com.oneclickshop.shop.model.ProductOrder;
import com.oneclickshop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, UUID> {
    List<ProductOrder> findByBuyer(User buyer);
}
