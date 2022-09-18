package com.example.internetshop.service;

import com.example.internetshop.model.OrderStatus;

public interface OrderService {
    String createOrder();
    OrderStatus showStatus();
}
