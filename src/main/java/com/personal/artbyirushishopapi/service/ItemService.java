package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import com.personal.artbyirushishopapi.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<ItemResponse> getAllItems(){
        try{
            List<Item> items = itemRepository.findAll();
            List<ItemDto> itemsList = items.stream().map(item -> {
                        ItemDto itemDto = new ItemDto();
                        itemDto.setId(item.getId());
                        itemDto.setName(item.getName());
                        itemDto.setPrice(item.getPrice());
                        itemDto.setImageUrl(item.getImageUrl());
                        itemDto.setDescription(item.getDescription());
                        return itemDto;
                    })
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ItemResponse(200,
                  "Success", "Items found Successfully", itemsList ), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ItemResponse(500, "Failure", "Error retrieving items",
                    Map.of("service-error", "Internal server error")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
