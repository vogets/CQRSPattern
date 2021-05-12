package com.appsdeveloperblog.estore.productsservice.rest.controller.command;


import com.appsdeveloperblog.estore.productsservice.commands.CreateProductCommand;
import com.appsdeveloperblog.estore.productsservice.rest.request.CreateProductModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {


    private final Environment environment;

    private final CommandGateway commandGateway;

    public ProductsCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody @Valid CreateProductModel productModel) throws ExecutionException, InterruptedException {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(productModel.getPrice())
                .title(productModel.getTitle())
                .quantity(productModel.getQuantity())
                .productId(UUID.randomUUID().toString()).build();
        return commandGateway.sendAndWait(createProductCommand);

    }


}
