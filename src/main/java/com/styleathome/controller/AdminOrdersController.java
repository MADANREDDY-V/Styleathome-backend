package com.styleathome.controller;

import com.styleathome.dto.AdminOrderStatusRequest;
import com.styleathome.entity.Order;
import com.styleathome.entity.OrderStatus;
import com.styleathome.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrdersController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody AdminOrderStatusRequest request
    ) {
        OrderStatus status = request.getStatus();
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}

