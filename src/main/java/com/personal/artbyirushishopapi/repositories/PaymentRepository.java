package com.personal.artbyirushishopapi.repositories;

import com.personal.artbyirushishopapi.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
