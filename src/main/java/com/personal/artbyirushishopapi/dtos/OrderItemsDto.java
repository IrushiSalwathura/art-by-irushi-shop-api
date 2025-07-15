package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDto {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private BigDecimal price;
}
