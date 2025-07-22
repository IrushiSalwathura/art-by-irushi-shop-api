package com.personal.artbyirushishopapi.dtos;

import com.personal.artbyirushishopapi.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    private OrderStatus status;
}
