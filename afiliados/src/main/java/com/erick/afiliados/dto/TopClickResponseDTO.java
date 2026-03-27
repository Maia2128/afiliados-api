package com.erick.afiliados.dto;

public class TopClickResponseDTO {

    private Long productId;
    private String productName;
    private long clicks;

    public TopClickResponseDTO(Long productId, String productName, long clicks) {
        this.productId = productId;
        this.productName = productName;
        this.clicks = clicks;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getClicks() {
        return clicks;
    }
}