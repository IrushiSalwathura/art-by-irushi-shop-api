package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.PaymentDto;
import com.personal.artbyirushishopapi.entities.Order;
import com.personal.artbyirushishopapi.entities.Payment;
import com.personal.artbyirushishopapi.repositories.OrderRepository;
import com.personal.artbyirushishopapi.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDto processPayment(@RequestBody PaymentDto paymentDto) {
        UUID paymentRef = UUID.randomUUID(); // This needs to retrieved from the payment gateway

        Order order = orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setReference(paymentRef.toString());
        payment.setTotal(paymentDto.getTotal());

        Payment savedPayment = paymentRepository.save(payment);
        paymentDto.setId(savedPayment.getId());
        paymentDto.setReference(paymentRef.toString());
        orderService.updateOrderStatus(order.getId(), "Payment Successful");
        return paymentDto;
    }
}
