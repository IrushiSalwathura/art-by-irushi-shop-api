package com.personal.artbyirushishopapi.dtos;

import com.personal.artbyirushishopapi.entities.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private String description;
    private Date date;
    private String status;
    private Long customerId;
}
