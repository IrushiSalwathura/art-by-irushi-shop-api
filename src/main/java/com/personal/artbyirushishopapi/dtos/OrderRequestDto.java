package com.personal.artbyirushishopapi.dtos;

import com.personal.artbyirushishopapi.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private String description;
    private LocalDate date;
    private OrderStatus status;
    private Long customerId;
    private List<OrderItemsDto> orderItems;
}
