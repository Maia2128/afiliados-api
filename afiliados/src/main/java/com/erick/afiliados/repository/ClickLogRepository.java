package com.erick.afiliados.repository;

import com.erick.afiliados.entity.ClickLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickLogRepository extends JpaRepository<ClickLog, Long> {
    long countByProductId(Long productId);
}