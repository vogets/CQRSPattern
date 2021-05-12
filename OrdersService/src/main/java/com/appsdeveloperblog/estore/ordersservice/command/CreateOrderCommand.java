package com.appsdeveloperblog.estore.ordersservice.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderCommand {
    public final String orderId;
    private final String userId="27b95829-4f3f-4ddf-8983-151ba010e35b";
    private final String productId;
    private final int quantity;
    private final String addressId;
    private final OrderStatus orderStatus;


}
