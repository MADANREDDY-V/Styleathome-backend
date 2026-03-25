package com.styleathome.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "session_cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_cart_id", nullable = false)
    private SessionCart sessionCart;

    @ManyToOne
    @JoinColumn(name = "service_id") // Optional: maybe they're just booking consultation for a generic product
    private ServiceOffering serviceOffering;

    @ManyToOne
    @JoinColumn(name = "product_id") // Optional: specific fashion item they want consultation on
    private Product relatedProduct;

    @Column(nullable = false)
    private LocalDate sessionDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType sessionType;
}
