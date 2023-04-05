package com.example.internetshop.controller;

import com.example.internetshop.model.dto.TestBeanDao;
import com.example.internetshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final TestBeanDao testBeanDao;

    @GetMapping
    public ResponseEntity<?> login() {

        int number = testBeanDao.getNumber();

        System.out.println("NUMBER: " + number);

        String order = orderService.createOrder();

        return ResponseEntity
                .ok()
                .build();
    }
}
