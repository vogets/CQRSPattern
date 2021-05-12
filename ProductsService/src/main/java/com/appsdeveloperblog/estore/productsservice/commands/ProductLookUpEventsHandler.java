package com.appsdeveloperblog.estore.productsservice.commands;


import com.appsdeveloperblog.estore.productsservice.core.events.ProductCreatedEvent;
import com.appsdeveloperblog.estore.productsservice.entities.ProductLookUpEntity;
import com.appsdeveloperblog.estore.productsservice.repository.ProductLookUpRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookUpEventsHandler {

    ProductLookUpRepository productLookUpRepository;

    public ProductLookUpEventsHandler(ProductLookUpRepository productLookUpRepository) {
        this.productLookUpRepository = productLookUpRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookUpEntity productLookUpEntity = new ProductLookUpEntity(
                productCreatedEvent.getProductId(), productCreatedEvent.getTitle());

        productLookUpRepository.save(productLookUpEntity);
    }
}
