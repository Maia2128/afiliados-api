package com.erick.afiliados.dto;

public class MetricResponseDTO {

    private Long productId;
    private long clicks;

    public MetricResponseDTO(Long productId, long clicks) {
        this.productId = productId;
        this.clicks = clicks;
    }

    public Long getProductId() {
        return productId;
    }

    public long getClicks() {
        return clicks;
    }
}