package com.appsdeveloperblog.estore.productsservice.repository;

import com.appsdeveloperblog.estore.productsservice.entities.ProductLookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookUpRepository extends JpaRepository<ProductLookUpEntity, String> {

    ProductLookUpEntity findByProductIdOrTitle(String productId, String title);
}
