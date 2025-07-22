package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private double price;
    private String imageUrl;
    private String description;
}
