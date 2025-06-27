package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
