package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image_url;
    private String description;
}
