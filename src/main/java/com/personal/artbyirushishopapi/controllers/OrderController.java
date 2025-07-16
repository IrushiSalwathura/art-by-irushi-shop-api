package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.OrderRequestDto;
import com.personal.artbyirushishopapi.dtos.OrderStatusUpdateDto;
import com.personal.artbyirushishopapi.entities.Order;
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
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest){
        Order createOrder = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateDto statusDto){
        Order updateStatus = orderService.updateOrderStatus(id, statusDto.getStatus());
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }
}
