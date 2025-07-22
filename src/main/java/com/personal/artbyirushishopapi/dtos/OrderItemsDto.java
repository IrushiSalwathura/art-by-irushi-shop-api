package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

@Data
public class OrderItemsDto {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private double price;
}
