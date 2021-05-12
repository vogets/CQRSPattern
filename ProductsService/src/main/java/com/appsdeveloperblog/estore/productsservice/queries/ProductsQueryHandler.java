package com.appsdeveloperblog.estore.productsservice.queries;

import com.appsdeveloperblog.estore.productsservice.entities.ProductEntity;
import com.appsdeveloperblog.estore.productsservice.repository.ProductsRepository;
import com.appsdeveloperblog.estore.productsservice.rest.request.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {

    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery findProductQuery) {
        List<ProductRestModel> listOfProducts = new ArrayList<>();
        List<ProductEntity> products = productsRepository.findAll();

        for (ProductEntity productEntity : products) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            listOfProducts.add(productRestModel);
        }
        return listOfProducts;
    }
}
