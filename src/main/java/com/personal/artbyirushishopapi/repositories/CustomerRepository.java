package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
}
