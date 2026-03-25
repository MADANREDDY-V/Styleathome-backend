package com.styleathome.controller;

import com.styleathome.entity.Booking;
import com.styleathome.entity.BookingStatus;
import com.styleathome.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
public class AdminSessionBookingsController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> listBookings(@RequestParam(required = false) BookingStatus status) {
        List<Booking> all = bookingService.getAllBookings();
        if (status == null) {
            return ResponseEntity.ok(all);
        }
        return ResponseEntity.ok(all.stream().filter(b -> b.getStatus() == status).collect(Collectors.toList()));
    }

    @PostMapping("/{bookingId}/approve")
    public ResponseEntity<Booking> approve(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId, BookingStatus.CONFIRMED));
    }

    @PostMapping("/{bookingId}/reject")
    public ResponseEntity<Booking> reject(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId, BookingStatus.CANCELLED));
    }
}

