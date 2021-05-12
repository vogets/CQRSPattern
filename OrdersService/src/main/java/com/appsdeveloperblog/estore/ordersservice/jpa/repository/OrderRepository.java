package com.appsdeveloperblog.estore.ordersservice.jpa.repository;

import com.appsdeveloperblog.estore.ordersservice.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {
}
