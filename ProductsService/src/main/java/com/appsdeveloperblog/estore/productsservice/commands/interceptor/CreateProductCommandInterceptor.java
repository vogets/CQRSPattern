package com.appsdeveloperblog.estore.productsservice.commands.interceptor;


import com.appsdeveloperblog.estore.productsservice.commands.CreateProductCommand;
import com.appsdeveloperblog.estore.productsservice.entities.ProductLookUpEntity;
import com.appsdeveloperblog.estore.productsservice.repository.ProductLookUpRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger log = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);
    private final ProductLookUpRepository productLookUpRepository;

    public CreateProductCommandInterceptor(ProductLookUpRepository productLookUpRepository) {
        this.productLookUpRepository = productLookUpRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                log.info("Intercepted command Type: " + command.getPayloadType());
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price Cannot be Less than or Equal to zero");
                }
                if (createProductCommand.getTitle() == null ||
                        createProductCommand.getTitle().isEmpty()) {
                    throw new IllegalArgumentException("Title Cannot be Blank");
                }
                ProductLookUpEntity productLookUpEntity = productLookUpRepository.findByProductIdOrTitle(
                        createProductCommand.getProductId(),
                        createProductCommand.getTitle());
                if (productLookUpEntity != null) {
                    throw new IllegalStateException("Product Id: " + createProductCommand.getProductId()
                            + " Or Product Title : " + createProductCommand.getTitle() + " Already exists");
                }
            }
            return command;
        };
    }
}
