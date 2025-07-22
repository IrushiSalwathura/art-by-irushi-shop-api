package com.personal.artbyirushishopapi.dtos;

import com.personal.artbyirushishopapi.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;
    private String description;
    private LocalDate date;
    private OrderStatus status;
    private Long customerId;
}
