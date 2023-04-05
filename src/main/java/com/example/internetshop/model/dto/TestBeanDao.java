package com.example.internetshop.model.dto;

import com.example.internetshop.model.InjectRandomInt;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestBeanDao {

    @InjectRandomInt(value = 5)
    private int number;
}
