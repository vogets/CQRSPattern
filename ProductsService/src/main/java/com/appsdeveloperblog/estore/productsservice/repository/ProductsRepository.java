package com.appsdeveloperblog.estore.productsservice.repository;

import com.appsdeveloperblog.estore.productsservice.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId);

    ProductEntity findByProductIdOrTitle(String productId, String productTitle);
}
