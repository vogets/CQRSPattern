package com.appsdeveloperblog.estore.ordersservice.rest.InputRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputPayload {
    private String productId;
    private Integer quantity;
    private String addressId;
}
