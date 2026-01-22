package com.sb.springboot_mvc_ecommerce.controller;

import com.sb.springboot_mvc_ecommerce.model.Product;
import com.sb.springboot_mvc_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    //get method for all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    //get method for search by id
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
    if(product.getId() > 0 ){
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    else {
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }


}
