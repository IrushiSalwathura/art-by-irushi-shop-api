package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.OrderDto;
import com.personal.artbyirushishopapi.mappers.OrderMapper;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import com.personal.artbyirushishopapi.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto, UriComponentsBuilder uriBuilder){
        var customer = customerRepository.findById(orderDto.getCustomerId()).orElse(null);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }
        var order = orderMapper.toEntity(orderDto);
        order.setCustomer(customer);
        orderRepository.save(order);
        orderDto.setId(order.getId());

        var uri = uriBuilder.path("/orders/{id}").buildAndExpand(orderDto.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDto);
    }
}
