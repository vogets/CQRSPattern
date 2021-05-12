package com.appsdeveloperblog.estore.productsservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "productlookup")

public class ProductLookUpEntity implements Serializable {

    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
