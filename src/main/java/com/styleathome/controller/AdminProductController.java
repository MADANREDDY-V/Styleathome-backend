package com.styleathome.controller;

import com.styleathome.dto.AdminProductRequest;
import com.styleathome.entity.Product;
import com.styleathome.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            Pageable pageable
    ) {
        List<Product> filtered = productService.filterProducts(q, category, type);
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min(start + pageable.getPageSize(), filtered.size());
        List<Product> pageSlice = start > end ? List.of() : filtered.subList(start, end);
        return ResponseEntity.ok(new PageImpl<>(pageSlice, pageable, filtered.size()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody AdminProductRequest request) {
        Product p = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .type(request.getType())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                // Admin create: rating can be adjusted later
                .rating(4.5)
                .build();
        return ResponseEntity.ok(productService.createProduct(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody AdminProductRequest request) {
        Product existing = productService.getProductById(id);
        Product payload = Product.builder()
                .id(existing.getId())
                .rating(existing.getRating())
                .name(request.getName())
                .category(request.getCategory())
                .type(request.getType())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                .build();
        return ResponseEntity.ok(productService.updateProduct(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

