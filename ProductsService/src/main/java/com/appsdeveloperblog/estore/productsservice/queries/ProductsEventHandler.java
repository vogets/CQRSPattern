package com.appsdeveloperblog.estore.productsservice.queries;

import com.appsdeveloperblog.estore.productsservice.core.events.ProductCreatedEvent;
import com.appsdeveloperblog.estore.productsservice.entities.ProductEntity;
import com.appsdeveloperblog.estore.productsservice.repository.ProductsRepository;
import lombok.extern.java.Log;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Log
@ProcessingGroup("product-group")
public class ProductsEventHandler {

    private final ProductsRepository productsRepository;

    public ProductsEventHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handleException(Exception e) throws Exception {
    log.info("Exception Occured is: "+e.getMessage());
    throw e;
    }
    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e)
    {
        log.info("Exception Occured is: "+e.getMessage());
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        try
        {
            productsRepository.save(productEntity);
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }


    }
}
