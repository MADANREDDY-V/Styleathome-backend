package com.styleathome.controller;

import com.styleathome.entity.Booking;
import com.styleathome.security.CustomUserDetails;
import com.styleathome.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import com.styleathome.dto.TimeSlot;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/confirm")
    public ResponseEntity<List<Booking>> confirmBookings(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(bookingService.confirmSessionCart(userDetails.getUser().getId()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Booking>> getUserBookings(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(bookingService.getUserBookings(userDetails.getUser().getId()));
    }

    @GetMapping("/slots/{serviceId}")
    public ResponseEntity<List<TimeSlot>> getAvailableSlots(@PathVariable Long serviceId) {
        List<TimeSlot> slots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
        
        long idCounter = 1;
        for (int day = 0; day < 3; day++) {
            for (int hour = 0; hour < 4; hour++) {
                LocalDateTime start = now.plusDays(day).plusHours(hour * 2);
                LocalDateTime end = start.plusHours(1);
                slots.add(new TimeSlot(idCounter++, start, end, false));
            }
        }
        return ResponseEntity.ok(slots);
    }
}
