package com.appsdeveloperblog.estore.productsservice.rest.controller.query;


import com.appsdeveloperblog.estore.productsservice.queries.FindProductQuery;
import com.appsdeveloperblog.estore.productsservice.rest.request.ProductRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<ProductRestModel>> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        CompletableFuture<List<ProductRestModel>> productsList = queryGateway
                .query(findProductQuery,
                        ResponseTypes.multipleInstancesOf(ProductRestModel.class));

        return productsList;
    }
}
