package com.erick.afiliados.controller;

import com.erick.afiliados.dto.MetricResponseDTO;
import com.erick.afiliados.dto.TopClickResponseDTO;
import com.erick.afiliados.service.MetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricsService service;

    public MetricsController(MetricsService service) {
        this.service = service;
    }

    @GetMapping("/product/{id}")
    public MetricResponseDTO getMetrics(@PathVariable Long id) {
        long clicks = service.getClicksByProduct(id);
        return new MetricResponseDTO(id, clicks);
    }

    @GetMapping("/top")
    public List<TopClickResponseDTO> getTopClicks() {
        return service.getTopClicks();
    }
}