package com.erick.afiliados.service;

import com.erick.afiliados.dto.TopClickResponseDTO;
import com.erick.afiliados.repository.ClickLogRepository;
import com.erick.afiliados.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MetricsService {

    private final ClickLogRepository clickLogRepository;
    private final ProductRepository productRepository;

    public MetricsService(ClickLogRepository clickLogRepository, ProductRepository productRepository) {
        this.clickLogRepository = clickLogRepository;
        this.productRepository = productRepository;
    }

    public long getClicksByProduct(Long productId) {
        return clickLogRepository.countByProductId(productId);
    }

    public List<TopClickResponseDTO> getTopClicks() {
        return productRepository.findAll()
                .stream()
                .map(product -> new TopClickResponseDTO(
                        product.getId(),
                        product.getName(),
                        clickLogRepository.countByProductId(product.getId())
                ))
                .sorted(Comparator.comparingLong(TopClickResponseDTO::getClicks).reversed())
                .toList();
    }

    public TopClickResponseDTO getTopProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> new TopClickResponseDTO(
                        product.getId(),
                        product.getName(),
                        clickLogRepository.countByProductId(product.getId())
                ))
                .sorted(Comparator.comparingLong(TopClickResponseDTO::getClicks).reversed())
                .findFirst()
                .orElse(null);
    }
}