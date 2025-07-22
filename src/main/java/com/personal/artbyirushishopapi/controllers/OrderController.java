package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.OrderRequestDto;
import com.personal.artbyirushishopapi.dtos.OrderStatusUpdateDto;
import com.personal.artbyirushishopapi.entities.Order;
import com.personal.artbyirushishopapi.response.OrderResponse;
import com.personal.artbyirushishopapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequestDto orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable Long id, @RequestBody OrderStatusUpdateDto statusDto){
        return orderService.placeOrder(id, statusDto.getStatus());
    }
}
