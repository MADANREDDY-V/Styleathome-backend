package com.styleathome.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    private Double rating;

    private String imageUrl;

    private String category;

    /**
     * Garment type shown in admin (shirt, jeans, saree, etc).
     * Kept nullable because existing rows might not have it yet.
     */
    private String type;
}
