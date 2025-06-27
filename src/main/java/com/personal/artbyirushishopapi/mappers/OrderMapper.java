package com.personal.artbyirushishopapi.mappers;

import com.personal.artbyirushishopapi.dtos.OrderDto;
import com.personal.artbyirushishopapi.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source="customer.id", target = "customerId")
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
