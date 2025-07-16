package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.mappers.ItemMapper;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDto> getAllItems(){
        List<Item> items = itemRepository.findAll();
        return items.stream().map(item -> {
                        ItemDto itemDto = new ItemDto();
                        itemDto.setName(item.getName());
                        itemDto.setPrice(item.getPrice());
                        itemDto.setImage_url(item.getImage_url());
                        itemDto.setDescription(item.getDescription());
                        return itemDto;
                        })
                .collect(Collectors.toList());
    }
}
