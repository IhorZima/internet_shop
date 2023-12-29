package com.example.internetshop.service.impl;

import com.example.internetshop.model.Order;
import com.example.internetshop.model.OrderStatus;
import com.example.internetshop.service.OrderService;
import com.example.internetshop.service.logging.LogOnTheFly;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @LogOnTheFly
    public String createOrder() {
        // TODO: save to DB
        LOGGER.info("Creating order...");
        LOGGER.info("Saving order...");
        return new Order().toString();
    }

    @Override
    public OrderStatus showStatus() {
        return null;
    }
}
