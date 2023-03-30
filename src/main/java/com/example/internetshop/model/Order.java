package com.example.internetshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private Long id;
    private int totalPrice;
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
