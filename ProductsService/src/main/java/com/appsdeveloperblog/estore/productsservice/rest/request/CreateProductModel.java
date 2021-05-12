package com.appsdeveloperblog.estore.productsservice.rest.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


@Data
public class CreateProductModel {
    @NotBlank(message = "Title Cannot Be Blank")
    private String title;
    @Min(value = 1, message = "Price Cannot Be Lower Than 1")
    private BigDecimal price;
    @Min(value = 1, message = "Quantity Should be Minimum 1")
    @Max(value = 5, message = "Quantity Cannot Exceed 5")
    private Integer quantity;
}
