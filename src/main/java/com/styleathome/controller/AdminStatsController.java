package com.styleathome.controller;

import com.styleathome.dto.AdminStatsResponse;
import com.styleathome.entity.Booking;
import com.styleathome.entity.Order;
import com.styleathome.repository.BookingRepository;
import com.styleathome.repository.OrderRepository;
import com.styleathome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminStatsController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BookingRepository bookingRepository;

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsResponse> stats() {
        long totalUsers = userRepository.count();
        List<Order> orders = orderRepository.findAll();
        long totalOrders = orders.size();
        double totalRevenue = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        long totalBookings = bookingRepository.count();

        return ResponseEntity.ok(AdminStatsResponse.builder()
                .totalUsers(totalUsers)
                .totalOrders(totalOrders)
                .totalRevenue(totalRevenue)
                .totalBookings(totalBookings)
                .build());
    }
}

