package com.sb.springboot_mvc_ecommerce.model.dto;

import java.util.List;

public record OrderRequest(
      String customerName,
      String email,
      List<OrderItemRequest> items
) {
}
