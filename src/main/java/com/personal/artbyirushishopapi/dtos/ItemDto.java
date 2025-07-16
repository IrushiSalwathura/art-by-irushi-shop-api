package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {
    private String name;
    private double price;
    private String image_url;
    private String description;
}
