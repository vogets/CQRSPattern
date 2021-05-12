package com.appsdeveloperblog.estore.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {
    private final String orderId;
    private final String userId;

    private final String productId;
    private final int quantity;
}
