package com.styleathome.controller;

import com.styleathome.dto.AddToCartRequest;
import com.styleathome.entity.Cart;
import com.styleathome.security.CustomUserDetails;
import com.styleathome.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCartByUserId(userDetails.getUser().getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userDetails.getUser().getId(), request));
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Cart> removeCartItem(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeCartItem(userDetails.getUser().getId(), itemId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Cart> clearCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(cartService.clearCart(userDetails.getUser().getId()));
    }
}
