package com.erick.afiliados.dto;

public class DashboardSummaryDTO {

    private long totalProducts;
    private long activeProducts;
    private long totalClicks;
    private String topProductName;

    public DashboardSummaryDTO(long totalProducts, long activeProducts, long totalClicks, String topProductName) {
        this.totalProducts = totalProducts;
        this.activeProducts = activeProducts;
        this.totalClicks = totalClicks;
        this.topProductName = topProductName;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public long getActiveProducts() {
        return activeProducts;
    }

    public long getTotalClicks() {
        return totalClicks;
    }

    public String getTopProductName() {
        return topProductName;
    }
}