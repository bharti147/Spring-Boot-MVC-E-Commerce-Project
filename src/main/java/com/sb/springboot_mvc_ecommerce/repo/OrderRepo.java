package com.sb.springboot_mvc_ecommerce.repo;

import com.sb.springboot_mvc_ecommerce.model.Order;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    Optional<Order> findById(String orderId);



}
