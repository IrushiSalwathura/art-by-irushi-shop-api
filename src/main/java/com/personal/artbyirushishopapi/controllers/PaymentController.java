package com.personal.artbyirushishopapi.controllers;

import com.personal.artbyirushishopapi.dtos.PaymentDto;
import com.personal.artbyirushishopapi.entities.Payment;
import com.personal.artbyirushishopapi.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto responsePaymentDto = paymentService.processPayment(paymentDto);
        return new ResponseEntity<>(responsePaymentDto, HttpStatus.CREATED);
    }
}
