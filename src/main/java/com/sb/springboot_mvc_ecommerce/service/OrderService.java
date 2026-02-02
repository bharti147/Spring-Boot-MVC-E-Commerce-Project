package com.sb.springboot_mvc_ecommerce.service;

import com.sb.springboot_mvc_ecommerce.model.Order;
import com.sb.springboot_mvc_ecommerce.model.OrderItem;
import com.sb.springboot_mvc_ecommerce.model.Product;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderItemRequest;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderItemResponse;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderRequest;
import com.sb.springboot_mvc_ecommerce.model.dto.OrderResponse;
import com.sb.springboot_mvc_ecommerce.repo.OrderRepo;
import com.sb.springboot_mvc_ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;

    //place order
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

       for(OrderItemRequest itemReq : orderRequest.items()){
           Product product = productRepo.findById(itemReq.productId())
                           .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .quantity(itemReq.quantity())
                    .order(order)
                    .build();

            orderItems.add(orderItem);
       }

        order.setItems(orderItems);
        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem orderItem  : savedOrder.getItems() ){
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    orderItem.getProduct().getName(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }


        OrderResponse orderResponse = new OrderResponse(
            savedOrder.getOrderId(),
            savedOrder.getCustomerName(),
            savedOrder.getEmail(),
            savedOrder.getStatus(),
            savedOrder.getOrderDate(),
            itemResponses
        );

        return orderResponse;
    }

    //get all orders
    public List<OrderResponse> getAllOrders(){

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();


        for(Order order: orders){

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for(OrderItem orderItem : order.getItems()){
                  OrderItemResponse orderItemResponse = new OrderItemResponse(
                          orderItem.getProduct().getName(),
                          orderItem.getQuantity(),
                          orderItem.getTotalPrice()
                  );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );

            orderResponses.add(orderResponse);
        }



  return orderResponses;
    }
}
