package com.styleathome.repository;

import com.styleathome.entity.SessionCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessionCartRepository extends JpaRepository<SessionCart, Long> {
    Optional<SessionCart> findByUserId(Long userId);
}
