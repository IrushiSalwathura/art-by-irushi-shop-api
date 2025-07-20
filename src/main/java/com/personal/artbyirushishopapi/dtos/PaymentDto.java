package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

@Data
public class PaymentDto {
    private Long id;
    private String reference;
    private double total;
    private Long orderId;
}
