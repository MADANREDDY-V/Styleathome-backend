package com.styleathome.dto;

import com.styleathome.entity.OrderStatus;
import lombok.Data;

@Data
public class AdminOrderStatusRequest {
    private OrderStatus status;
}

