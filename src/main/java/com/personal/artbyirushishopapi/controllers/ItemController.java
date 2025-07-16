package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.mappers.ItemMapper;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import com.personal.artbyirushishopapi.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

}
