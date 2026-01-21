package com.sb.springboot_mvc_ecommerce.repo;


import com.sb.springboot_mvc_ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
