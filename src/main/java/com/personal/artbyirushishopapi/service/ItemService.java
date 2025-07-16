package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.mappers.ItemMapper;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDto> getAllItems(){
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::toDto).toList();
    }
}
