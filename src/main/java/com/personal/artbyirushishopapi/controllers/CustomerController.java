package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.CustomerDto;
import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import com.personal.artbyirushishopapi.mappers.CustomerMapper;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
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

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody RegisterCustomerRequest request, UriComponentsBuilder uriBuilder){
        var customer = customerMapper.toEntity(request);

        if((!customerRepository.existsByEmail(request.getEmail())) && (!customerRepository.existsByDob(request.getDob()))){
            customerRepository.save(customer);
            var customerDto = customerMapper.toDto(customer);
            var uri = uriBuilder.path("/customers/{id}").buildAndExpand(customerDto.getId()).toUri();
            return ResponseEntity.created(uri).body(customerDto);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The customer is already available");
        }
    }
}
