package com.styleathome.service;

import com.styleathome.entity.*;
import com.styleathome.exception.BadRequestException;
import com.styleathome.repository.BookingRepository;
import com.styleathome.repository.SessionCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SessionCartService sessionCartService;
    private final SessionCartRepository sessionCartRepository;

    @Transactional
    public List<Booking> confirmSessionCart(Long userId) {
        SessionCart cart = sessionCartService.getSessionCartByUserId(userId);

        if (cart.getSessionCartItems().isEmpty()) {
            throw new BadRequestException("Cannot confirm an empty session booking cart.");
        }

        List<Booking> confirmedBookings = cart.getSessionCartItems().stream().map(item -> {
            Booking booking = new Booking();
            booking.setUser(cart.getUser());
            booking.setServiceOffering(item.getServiceOffering());
            booking.setRelatedProduct(item.getRelatedProduct());
            booking.setSessionDate(item.getSessionDate());
            booking.setStartTime(item.getStartTime());
            booking.setSessionType(item.getSessionType());
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.setBookingCreatedAt(LocalDateTime.now());
            return booking;
        }).collect(Collectors.toList());

        List<Booking> savedBookings = bookingRepository.saveAll(confirmedBookings);

        // Clear the session cart after booking confirmed
        cart.getSessionCartItems().clear();
        sessionCartRepository.save(cart);

        return savedBookings;
    }

    /**
     * Creates bookings with status PENDING from the user's session cart.
     * This is used so admin can later approve/reject these session requests.
     */
    @Transactional
    public List<Booking> createPendingBookingsFromSessionCart(Long userId) {
        SessionCart cart = sessionCartService.getSessionCartByUserId(userId);
        if (cart.getSessionCartItems().isEmpty()) {
            return List.of();
        }

        List<Booking> pendingBookings = cart.getSessionCartItems().stream().map(item -> {
            Booking booking = new Booking();
            booking.setUser(cart.getUser());
            booking.setServiceOffering(item.getServiceOffering());
            booking.setRelatedProduct(item.getRelatedProduct());
            booking.setSessionDate(item.getSessionDate());
            booking.setStartTime(item.getStartTime());
            booking.setSessionType(item.getSessionType());
            booking.setStatus(BookingStatus.PENDING);
            booking.setBookingCreatedAt(LocalDateTime.now());
            return booking;
        }).collect(Collectors.toList());

        List<Booking> saved = bookingRepository.saveAll(pendingBookings);

        cart.getSessionCartItems().clear();
        sessionCartRepository.save(cart);

        return saved;
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserIdOrderBySessionDateDesc(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Transactional
    public Booking updateBookingStatus(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new com.styleathome.exception.ResourceNotFoundException("Booking not found: " + bookingId));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }
}
