package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
