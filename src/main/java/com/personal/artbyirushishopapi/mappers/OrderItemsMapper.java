package com.personal.artbyirushishopapi.mappers;

import com.personal.artbyirushishopapi.dtos.OrderItemsDto;
import com.personal.artbyirushishopapi.entities.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemsMapper {
    OrderItemsDto toDto(OrderItem orderItem);
    OrderItem toEntity(OrderItemsDto orderItemsDto);
}
