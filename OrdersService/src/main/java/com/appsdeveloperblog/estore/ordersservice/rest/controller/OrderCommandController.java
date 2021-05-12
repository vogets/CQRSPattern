package com.appsdeveloperblog.estore.ordersservice.rest.controller;

import com.appsdeveloperblog.estore.ordersservice.command.CreateOrderCommand;
import com.appsdeveloperblog.estore.ordersservice.command.OrderStatus;
import com.appsdeveloperblog.estore.ordersservice.rest.InputRequest.InputPayload;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway)
    {
        this.commandGateway=commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody InputPayload payload)
    {
        CreateOrderCommand createOrderCommand=CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(payload.getProductId())
                .addressId(payload.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .quantity(payload.getQuantity()).build();
        return commandGateway.sendAndWait(createOrderCommand);
    }
}
