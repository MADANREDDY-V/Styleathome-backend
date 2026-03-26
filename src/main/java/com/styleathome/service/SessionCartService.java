package com.styleathome.service;

import com.styleathome.dto.BookSessionRequest;
import com.styleathome.entity.*;
import com.styleathome.exception.ResourceNotFoundException;
import com.styleathome.exception.BadRequestException;
import com.styleathome.repository.ProductRepository;
import com.styleathome.repository.ServiceOfferingRepository;
import com.styleathome.repository.SessionCartRepository;
import com.styleathome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SessionCartService {

    private final SessionCartRepository sessionCartRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public SessionCart getSessionCartByUserId(Long userId) {
        Optional<SessionCart> opt = sessionCartRepository.findByUserId(userId);
        SessionCart cart = opt.orElseGet(() -> {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            SessionCart newCart = new SessionCart();
            newCart.setUser(user);
            newCart.setSessionCartItems(new ArrayList<>());
            return sessionCartRepository.save(newCart);
        });

        if (cart.getSessionCartItems() == null) {
            cart.setSessionCartItems(new ArrayList<>());
        }

        return cart;
    }

    @Transactional
    public SessionCart addToSessionCart(Long userId, BookSessionRequest request) {
        if (request.getServiceOfferingId() == null && request.getRelatedProductId() == null) {
            throw new BadRequestException("Must provide either a service ID or a product ID to book a session.");
        }

        SessionCart cart = getSessionCartByUserId(userId);
        
        ServiceOffering serviceOffering = null;
        if (request.getServiceOfferingId() != null) {
            serviceOffering = serviceOfferingRepository.findById(request.getServiceOfferingId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        }

        Product relatedProduct = null;
        if (request.getRelatedProductId() != null) {
            relatedProduct = productRepository.findById(request.getRelatedProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        }

        SessionCartItem newItem = new SessionCartItem();
        newItem.setSessionCart(cart);
        newItem.setServiceOffering(serviceOffering);
        newItem.setRelatedProduct(relatedProduct);
        newItem.setSessionDate(request.getSessionDate());
        newItem.setStartTime(request.getStartTime());
        newItem.setSessionType(request.getSessionType());
                
        cart.getSessionCartItems().add(newItem);
        return sessionCartRepository.save(cart);
    }

    @Transactional
    public SessionCart removeSessionCartItem(Long userId, Long itemId) {
        SessionCart cart = getSessionCartByUserId(userId);
        cart.getSessionCartItems().removeIf(item -> item.getId().equals(itemId));
        return sessionCartRepository.save(cart);
    }
    
    @Transactional
    public SessionCart clearSessionCart(Long userId) {
        SessionCart cart = getSessionCartByUserId(userId);
        cart.getSessionCartItems().clear();
        return sessionCartRepository.save(cart);
    }
}
