package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByDob(Date dob);
}
