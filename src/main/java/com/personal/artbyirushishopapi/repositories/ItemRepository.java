package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
