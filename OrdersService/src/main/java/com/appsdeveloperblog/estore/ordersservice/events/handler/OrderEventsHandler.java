package com.appsdeveloperblog.estore.ordersservice.events.handler;

import com.appsdeveloperblog.estore.ordersservice.events.OrderCreatedEvent;
import com.appsdeveloperblog.estore.ordersservice.jpa.entity.OrderEntity;
import com.appsdeveloperblog.estore.ordersservice.jpa.repository.OrderRepository;
import lombok.Data;
import lombok.extern.java.Log;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Data
@Log
@ProcessingGroup("order-group")
public class OrderEventsHandler {
    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
            public void handleExceptions(Exception e)
    {
        log.info("Exception Occured while creating order: "+e.getMessage());
    }
    @EventHandler
    public void on(OrderCreatedEvent createdEvent)
    {
        OrderEntity orderEntity=new OrderEntity();
        BeanUtils.copyProperties(createdEvent,orderEntity);
        try
        {
            orderRepository.save(orderEntity);
        }
        catch (Exception exception)
        {
            log.info("Exception Occured while Creating Order: "+exception.getMessage());
        }
    }
}
