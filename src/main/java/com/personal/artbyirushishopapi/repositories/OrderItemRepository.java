package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.dtos.OrderItemsDto;
import com.personal.artbyirushishopapi.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemsByOrderId(Long orderId);
}
