package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.CustomerDto;
import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import com.personal.artbyirushishopapi.entities.Customer;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public RegisterCustomerRequest createCustomer(RegisterCustomerRequest customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setDob(customerDto.getDob());
        customer.setPhone(customerDto.getPhone());

        customerRepository.save(customer);
        return customerDto;
    }
}
