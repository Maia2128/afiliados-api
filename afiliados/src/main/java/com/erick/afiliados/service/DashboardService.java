package com.erick.afiliados.service;

import com.erick.afiliados.dto.DashboardSummaryDTO;
import com.erick.afiliados.dto.TopClickResponseDTO;
import com.erick.afiliados.repository.ClickLogRepository;
import com.erick.afiliados.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProductRepository productRepository;
    private final ClickLogRepository clickLogRepository;
    private final MetricsService metricsService;

    public DashboardService(ProductRepository productRepository,
                            ClickLogRepository clickLogRepository,
                            MetricsService metricsService) {
        this.productRepository = productRepository;
        this.clickLogRepository = clickLogRepository;
        this.metricsService = metricsService;
    }

    public DashboardSummaryDTO getSummary() {
        long totalProducts = productRepository.count();
        long activeProducts = productRepository.findByActiveTrueOrderByQueueOrderAsc().size();
        long totalClicks = clickLogRepository.count();

        TopClickResponseDTO topProduct = metricsService.getTopProduct();
        String topProductName = topProduct != null ? topProduct.getProductName() : "Nenhum";

        return new DashboardSummaryDTO(
                totalProducts,
                activeProducts,
                totalClicks,
                topProductName
        );
    }
}