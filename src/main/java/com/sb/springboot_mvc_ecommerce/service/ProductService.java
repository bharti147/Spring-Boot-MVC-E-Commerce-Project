package com.sb.springboot_mvc_ecommerce.service;

import com.sb.springboot_mvc_ecommerce.model.Product;
import com.sb.springboot_mvc_ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }

    public void deleteProductById(int id){
         repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }



}
