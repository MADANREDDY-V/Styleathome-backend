package com.styleathome.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session_carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "sessionCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionCartItem> sessionCartItems = new ArrayList<>();
}
