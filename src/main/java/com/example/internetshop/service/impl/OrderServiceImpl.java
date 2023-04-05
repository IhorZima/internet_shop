package com.example.internetshop.service.impl;

import com.example.internetshop.model.Order;
import com.example.internetshop.model.OrderStatus;
import com.example.internetshop.repository.OrderRepository;
import com.example.internetshop.service.LoggingService;
import com.example.internetshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final LoggingService<Order> orderLoggingService;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public String createOrder() {
        Order order = orderRepository.save(new Order());
        orderLoggingService.logEntity(order);
        return order.toString();
    }

    @Override
    public OrderStatus showStatus() {
        return null;


        // new OrderService()
        // set set set set set
        // put object in context

        // new OrderService()
        // set set
    }
}
