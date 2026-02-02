package com.sb.springboot_mvc_ecommerce.controller;

import com.sb.springboot_mvc_ecommerce.model.dto.OrderRequest;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderResponse;
import com.sb.springboot_mvc_ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //place order
    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
       OrderResponse orderResponse = orderService.placeOrder(orderRequest);

        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    //get all orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
          List<OrderResponse> orderResponseList = orderService.getAllOrders();
        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
    }

}
