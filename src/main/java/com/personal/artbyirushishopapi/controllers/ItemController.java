package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.response.ItemResponse;
import com.personal.artbyirushishopapi.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ItemResponse> getAllItems() {
        return itemService.getAllItems();
    }

}
