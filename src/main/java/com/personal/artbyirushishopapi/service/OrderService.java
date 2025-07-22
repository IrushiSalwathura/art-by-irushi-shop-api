package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.OrderDto;
import com.personal.artbyirushishopapi.dtos.OrderItemsDto;
import com.personal.artbyirushishopapi.dtos.OrderRequestDto;
import com.personal.artbyirushishopapi.entities.Customer;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.entities.Order;
import com.personal.artbyirushishopapi.entities.OrderItem;
import com.personal.artbyirushishopapi.enums.OrderStatus;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import com.personal.artbyirushishopapi.repositories.OrderItemRepository;
import com.personal.artbyirushishopapi.repositories.OrderRepository;
import com.personal.artbyirushishopapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public ResponseEntity<OrderResponse> createOrder(OrderRequestDto orderRequestDto) {
        try{
            Map<String, String> errorMap = validateOrder(orderRequestDto);
            if(!errorMap.isEmpty()){
                return new ResponseEntity<>(new OrderResponse(400, "Failure", "Order validation failed", errorMap), HttpStatus.BAD_REQUEST);
            }

            Customer customer = customerRepository.findById(orderRequestDto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Order order = new Order();
            order.setCustomer(customer);
            order.setDate(orderRequestDto.getDate());
            order.setDescription(orderRequestDto.getDescription());
            order.setStatus(orderRequestDto.getStatus());

            Order savedOrder = orderRepository.save(order); //new Id
            orderRequestDto.setId(savedOrder.getId());

            //Saving Order Items
            List<OrderItem> orderItems = new ArrayList<>();

            for (OrderItemsDto itemDto : orderRequestDto.getOrderItems()) {
                Item item = itemRepository.findById(itemDto.getItemId())
                        .orElseThrow(() -> new RuntimeException("Item not found"));

                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setPrice(item.getPrice() * itemDto.getQuantity());
                orderItem.setOrder(savedOrder); // make sure the new Id is saved.

                orderItems.add(orderItem);
                orderItemRepository.save(orderItem);
            }
            return new ResponseEntity<>(new OrderResponse(200,"Success", "Order created", orderRequestDto), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new OrderResponse(500, "Failure", "Order create failed",
                    Map.of("service-error", "Internal server error")),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Map<String, String> validateOrder(OrderRequestDto orderRequestDto) {
        Map<String, String> errorMap = new HashMap<>();
        handleMandatoryValidations(orderRequestDto, errorMap);
        handleFormatValidations(orderRequestDto, errorMap);
        return errorMap;
    }

    private void handleMandatoryValidations(OrderRequestDto orderRequestDto, Map<String, String> errorMap) {
        if(orderRequestDto.getId() == null || orderRequestDto.getId() <= 0)
            errorMap.put("orderId", "Order id must be mandatory and greater than zero");
        if(orderRequestDto.getDescription() == null || orderRequestDto.getDescription().isEmpty())
            errorMap.put("description", "Order description must be mandatory");
        if(orderRequestDto.getDate() == null)
            errorMap.put("date", "Order date must be mandatory");
        if(orderRequestDto.getStatus() == null )
            errorMap.put("status", "Order status must be mandatory");
        if(orderRequestDto.getCustomerId() == null || orderRequestDto.getCustomerId() <= 0)
            errorMap.put("customerId", "Customer id must be mandatory");
    }

    private void handleFormatValidations(OrderRequestDto orderRequestDto,  Map<String, String> errorMap) {
        //Length of description
        if(orderRequestDto.getDescription().length() < 3)
            errorMap.put("description", "Description too short");
        //Date must be in the past
        if(orderRequestDto.getDate().isAfter(LocalDate.now()))
            errorMap.put("date", "Date cannot be in the future");

    }

    public ResponseEntity<OrderResponse> placeOrder(Long id, OrderStatus newStatus) {
        try{
            //Checks if the order exists
            Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
            //Update the status
            order.setStatus(newStatus);
            orderRepository.save(order);
            //Capture the OrderItems of the order
            List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(order.getId());

            //TODO: refer stream and map functions in Collections API
//            List<OrderItemsDto> list = orderItems.stream().map(orderItem -> {
//                OrderItemsDto orderItemDtos = getOrderItemDtos(orderItem);
//                return orderItemDtos;
//            }).collect(Collectors.toList());

            //Loop through and store the order items List
            ArrayList<OrderItemsDto> orderItemsDtos = new ArrayList<>();
            orderItems.forEach(orderItem -> {
                OrderItemsDto orderItemDtos = getOrderItemDtos(orderItem);
                orderItemsDtos.add(orderItemDtos);
            });

            //Creating a new Dto and updating the values so the response contains the updated Order as data.
            OrderRequestDto orderDto = new OrderRequestDto();
            orderDto.setId(order.getId());
            orderDto.setStatus(newStatus);
            orderDto.setDate(order.getDate());
            orderDto.setDescription(order.getDescription());
            orderDto.setCustomerId(order.getCustomer().getId());
            orderDto.setOrderItems(orderItemsDtos);

            return new ResponseEntity<>(new OrderResponse(200, "Success", "Order updated", orderDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new OrderResponse(500, "Failure", "Order update failed",
                    Map.of("service-error", "Internal server error")),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private OrderItemsDto getOrderItemDtos(OrderItem orderItem) {
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setId(orderItem.getId());
        orderItemsDto.setOrderId(orderItem.getOrder().getId());
        orderItemsDto.setItemId(orderItem.getItem().getId());
        orderItemsDto.setQuantity(orderItem.getQuantity());
        orderItemsDto.setPrice(orderItem.getPrice());
        return orderItemsDto;
    }

}
