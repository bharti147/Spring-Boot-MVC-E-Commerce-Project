package com.sb.springboot_mvc_ecommerce.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {
}
