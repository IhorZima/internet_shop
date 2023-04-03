package com.example.internetshop.service;

import com.example.internetshop.model.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderFileLoggingService implements LoggingService<Order> {

    @Override
    public Order logEntity(Order order) {
        return null;
    }
}
