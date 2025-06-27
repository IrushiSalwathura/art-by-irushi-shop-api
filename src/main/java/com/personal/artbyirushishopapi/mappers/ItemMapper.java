package com.personal.artbyirushishopapi.mappers;

import com.personal.artbyirushishopapi.dtos.ItemDto;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.entities.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item item);
    Item toEntity(Order order);
}
