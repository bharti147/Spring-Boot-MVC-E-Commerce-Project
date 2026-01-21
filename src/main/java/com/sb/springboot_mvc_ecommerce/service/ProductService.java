package com.sb.springboot_mvc_ecommerce.service;

import com.sb.springboot_mvc_ecommerce.model.Product;
import com.sb.springboot_mvc_ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProduct(int id){
        return  repo.findById(id).orElse(new Product(-1));
    }
}
