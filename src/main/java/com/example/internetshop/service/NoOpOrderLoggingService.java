package com.example.internetshop.service;

import com.example.internetshop.model.Order;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class NoOpOrderLoggingService implements LoggingService<Order> {

    @Override
    public Order logEntity(Order entity) {
        // No-Op service
        return entity;
    }
}
