package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.CustomerDto;
import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import com.personal.artbyirushishopapi.entities.Customer;
import com.personal.artbyirushishopapi.mappers.CustomerMapper;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import com.personal.artbyirushishopapi.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody RegisterCustomerRequest request, UriComponentsBuilder uriBuilder){
        RegisterCustomerRequest createCustomer = customerService.createCustomer(request);
        return new ResponseEntity<>(createCustomer, HttpStatus.CREATED);
    }
}
