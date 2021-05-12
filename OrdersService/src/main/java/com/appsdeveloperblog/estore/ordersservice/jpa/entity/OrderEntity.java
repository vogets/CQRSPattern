package com.appsdeveloperblog.estore.ordersservice.jpa.entity;

import com.appsdeveloperblog.estore.ordersservice.command.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {
    @Id
    public String orderId;
    @Column(unique = true)
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
