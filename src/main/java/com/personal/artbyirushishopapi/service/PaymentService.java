package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.PaymentDto;
import com.personal.artbyirushishopapi.dtos.PaymentResponseDto;
import com.personal.artbyirushishopapi.entities.Order;
import com.personal.artbyirushishopapi.entities.Payment;
import com.personal.artbyirushishopapi.repositories.OrderRepository;
import com.personal.artbyirushishopapi.repositories.PaymentRepository;
import com.personal.artbyirushishopapi.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;
//    @Autowired
//    private OrderService orderService;
    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseEntity<PaymentResponse> processPayment(PaymentDto paymentDto) {
        try{
            Map<String, String> errorMap = validatePayment(paymentDto);
            if(!errorMap.isEmpty())
                return new ResponseEntity<>(new PaymentResponse(400, "Failure", "Payment Validation failed", errorMap), HttpStatus.BAD_REQUEST);

            UUID paymentRef = UUID.randomUUID(); // This needs to retrieved from the payment gateway
            System.out.println(paymentDto.getOrderId());
            Order order = orderRepository.findById(paymentDto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            Payment payment = new Payment();
            payment.setOrder(order);
            payment.setReference(paymentRef.toString());
            payment.setTotal(paymentDto.getTotal());

            Payment savedPayment = paymentRepository.save(payment);

            PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
            paymentResponseDto.setId(savedPayment.getId());
            paymentResponseDto.setReference(paymentRef.toString());
            paymentResponseDto.setTotal(paymentDto.getTotal());
            paymentResponseDto.setOrderId(paymentDto.getOrderId());

//            orderService.updateOrderStatus(order.getId(), OrderStatus.SUCCESS);

            return new ResponseEntity<>( new PaymentResponse(200, "Success", "Payment created", paymentResponseDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new PaymentResponse(500, "Failure", "Payment create failed",
                    Map.of("service-error", "Internal Server Error")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, String> validatePayment(PaymentDto paymentDto) {
        Map<String, String> errorMap = new HashMap<>();
        handleMandatoryValidations(paymentDto, errorMap);
        handleFormatValidations(paymentDto, errorMap);
        return errorMap;
    }

    private void handleMandatoryValidations(PaymentDto paymentDto, Map<String, String> errorMap) {
        if(paymentDto.getTotal() <= 0)
            errorMap.put("total", "Total must be mandatory and not a negative number");
        if(paymentDto.getOrderId() == null || paymentDto.getOrderId() <= 0)
            errorMap.put("orderId", "Order Id must be mandatory");
    }

    private void handleFormatValidations(PaymentDto paymentDto, Map<String, String> errorMap) {

    }

}
