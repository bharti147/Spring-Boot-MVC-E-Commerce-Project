package com.sb.springboot_mvc_ecommerce.controller;

import com.sb.springboot_mvc_ecommerce.model.Product;
import com.sb.springboot_mvc_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    //get method for image
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product = productService.getProduct(id);
        if(product.getId()>0){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //post method for adding product with image
        @PostMapping("/product")
        public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile imageFile)  {
            Product savedProduct = null;
            try {
                savedProduct = productService.addOrUpdateProduct(product, imageFile);
                return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
            }
            catch(Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    //put method
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile){
       Product updatedProduct = null;
      try{
         updatedProduct = productService.addOrUpdateProduct(product, imageFile);
          return new ResponseEntity<>("updated", HttpStatus.OK);
      }
      catch(Exception e){
          return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //delete method
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduceById(@PathVariable int id){
        Product product = productService.getProduct(id);
        if(product!=null){
            productService.deleteProductById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
