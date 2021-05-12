package com.appsdeveloperblog.estore.productsservice;

import com.appsdeveloperblog.estore.productsservice.commands.interceptor.CreateProductCommandInterceptor;
import com.appsdeveloperblog.estore.productsservice.core.errorhandler.ProductsServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateProductsCommandInterceptor(ApplicationContext applicationContext,
                                                         CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateProductCommandInterceptor.class));
    }

    @Autowired
    public void configure(EventProcessingConfigurer config)
    {
        config.registerListenerInvocationErrorHandler("product-group",conf->new ProductsServiceEventsErrorHandler());
    }

}
