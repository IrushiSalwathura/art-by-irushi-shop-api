package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private String description;
    private Date date;
    private String status;
    private Long customerId;
    private List<OrderItemsDto> orderItems;
}
