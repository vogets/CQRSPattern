package com.appsdeveloperblog.estore.ordersservice.events;

import com.appsdeveloperblog.estore.ordersservice.command.CreateOrderCommand;
import com.appsdeveloperblog.estore.ordersservice.command.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    public OrderAggregate() {
    }

    @CommandHandler
    OrderAggregate(CreateOrderCommand createOrderCommand)
    {
        //TODO-Add Validations

        OrderCreatedEvent orderCreatedEvent=new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }
    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent)
    {
        this.orderId=orderCreatedEvent.getOrderId();
        this.orderStatus=orderCreatedEvent.getOrderStatus();
        this.addressId=orderCreatedEvent.getAddressId();
        this.userId=orderCreatedEvent.getUserId();
        this.quantity=orderCreatedEvent.getQuantity();
        this.productId=orderCreatedEvent.getProductId();
    }
}
