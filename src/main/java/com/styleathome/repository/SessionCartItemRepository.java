package com.styleathome.repository;

import com.styleathome.entity.SessionCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionCartItemRepository extends JpaRepository<SessionCartItem, Long> {
}
