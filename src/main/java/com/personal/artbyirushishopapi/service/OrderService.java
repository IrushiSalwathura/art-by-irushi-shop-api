package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.OrderItemsDto;
import com.personal.artbyirushishopapi.dtos.OrderRequestDto;
import com.personal.artbyirushishopapi.entities.Customer;
import com.personal.artbyirushishopapi.entities.Item;
import com.personal.artbyirushishopapi.entities.Order;
import com.personal.artbyirushishopapi.entities.OrderItem;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import com.personal.artbyirushishopapi.repositories.ItemRepository;
import com.personal.artbyirushishopapi.repositories.OrderItemRepository;
import com.personal.artbyirushishopapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Order createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(orderRequestDto.getDate());
        order.setDescription(orderRequestDto.getDescription());
        order.setStatus(orderRequestDto.getStatus());

        orderRequestDto.setId(order.getId());
        Order savedOrder = orderRepository.save(order); //new Id

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

        return savedOrder;
    }

    public Order updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
