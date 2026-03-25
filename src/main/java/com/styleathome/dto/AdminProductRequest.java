package com.styleathome.dto;

import lombok.Data;

/**
 * Request payload for admin CRUD on products.
 * - category: Men / Women / Kids
 * - type: shirt / jeans / saree / etc
 */
@Data
public class AdminProductRequest {
    private String name;
    private String category;
    private String type;
    private Double price;
    private String imageUrl;
    private String description;
}

