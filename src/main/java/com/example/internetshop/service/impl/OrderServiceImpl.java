package com.example.internetshop.service.impl;

import com.example.internetshop.model.Order;
import com.example.internetshop.model.OrderStatus;
import com.example.internetshop.service.LoggingService;
import com.example.internetshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final LoggingService<Order> orderLoggingService;

    @Override
    public String createOrder() {
        orderLoggingService.logEntity(new Order());
        return null;
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
