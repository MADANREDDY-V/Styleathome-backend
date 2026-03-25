package com.styleathome.controller;

import com.styleathome.dto.BookSessionRequest;
import com.styleathome.entity.SessionCart;
import com.styleathome.security.CustomUserDetails;
import com.styleathome.service.SessionCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session-cart")
@RequiredArgsConstructor
public class SessionCartController {

    private final SessionCartService sessionCartService;

    @GetMapping
    public ResponseEntity<SessionCart> getSessionCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(sessionCartService.getSessionCartByUserId(userDetails.getUser().getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<SessionCart> addToSessionCart(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody BookSessionRequest request) {
        return ResponseEntity.ok(sessionCartService.addToSessionCart(userDetails.getUser().getId(), request));
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<SessionCart> removeSessionCartItem(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(sessionCartService.removeSessionCartItem(userDetails.getUser().getId(), itemId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<SessionCart> clearSessionCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(sessionCartService.clearSessionCart(userDetails.getUser().getId()));
    }
}
