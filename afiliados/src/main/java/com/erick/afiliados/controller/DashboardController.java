package com.erick.afiliados.controller;

import com.erick.afiliados.dto.DashboardSummaryDTO;
import com.erick.afiliados.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary() {
        return service.getSummary();
    }
}