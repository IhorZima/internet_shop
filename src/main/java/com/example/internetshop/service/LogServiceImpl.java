package com.example.internetshop.service;

import com.example.internetshop.model.Order;
import org.aspectj.weaver.ast.Or;

import java.nio.file.Files;

public class LogServiceImpl implements LogService<Order>{
    @Override
    public Order persistUserAction(Order order) {
        return null;
    }
}
