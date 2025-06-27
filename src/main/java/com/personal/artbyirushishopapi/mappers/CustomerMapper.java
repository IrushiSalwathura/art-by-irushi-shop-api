package com.personal.artbyirushishopapi.mappers;

import com.personal.artbyirushishopapi.dtos.CustomerDto;
import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import com.personal.artbyirushishopapi.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(RegisterCustomerRequest request);
}
