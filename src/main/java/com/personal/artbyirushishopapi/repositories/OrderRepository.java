package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
