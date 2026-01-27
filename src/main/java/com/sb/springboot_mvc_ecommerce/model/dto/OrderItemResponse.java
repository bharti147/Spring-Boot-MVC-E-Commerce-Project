package com.sb.springboot_mvc_ecommerce.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemResponse(
     String productName,
     int quantity,
     BigDecimal totalPrice
) {


}
