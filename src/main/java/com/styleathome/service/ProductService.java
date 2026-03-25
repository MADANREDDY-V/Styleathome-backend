package com.styleathome.service;

import com.styleathome.entity.Product;
import com.styleathome.exception.ResourceNotFoundException;
import com.styleathome.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> filterProducts(String q, String category, String type) {
        String query = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);
        String cat = category == null ? null : category.trim();
        String t = type == null ? null : type.trim();

        return productRepository.findAll().stream()
                .filter(p -> cat == null || Objects.equals(p.getCategory(), cat))
                .filter(p -> t == null || Objects.equals(p.getType(), t))
                .filter(p -> query.isEmpty()
                        || (p.getName() != null && p.getName().toLowerCase(Locale.ROOT).contains(query))
                        || (p.getDescription() != null && p.getDescription().toLowerCase(Locale.ROOT).contains(query)))
                .collect(Collectors.toList());
    }

    public Product updateProduct(Long id, Product payload) {
        Product existing = getProductById(id);
        existing.setName(payload.getName());
        existing.setDescription(payload.getDescription());
        existing.setPrice(payload.getPrice());
        existing.setRating(payload.getRating());
        existing.setImageUrl(payload.getImageUrl());
        existing.setCategory(payload.getCategory());
        existing.setType(payload.getType());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product existing = getProductById(id);
        productRepository.delete(existing);
    }
}
