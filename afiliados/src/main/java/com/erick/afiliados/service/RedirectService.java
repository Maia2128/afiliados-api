package com.erick.afiliados.service;

import com.erick.afiliados.entity.ClickLog;
import com.erick.afiliados.entity.Product;
import com.erick.afiliados.repository.ClickLogRepository;
import com.erick.afiliados.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RedirectService {

    private final ProductRepository productRepository;
    private final ClickLogRepository clickLogRepository;

    public RedirectService(ProductRepository productRepository,
                           ClickLogRepository clickLogRepository) {
        this.productRepository = productRepository;
        this.clickLogRepository = clickLogRepository;
    }

    public String registerClickAndGetUrl(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        ClickLog clickLog = new ClickLog();
        clickLog.setProductId(productId);
        clickLog.setClickedAt(LocalDateTime.now());
        clickLog.setSource("browser");

        clickLogRepository.save(clickLog);

        return product.getAffiliateUrl();
    }
}