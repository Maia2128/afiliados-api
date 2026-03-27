package com.erick.afiliados.service;

import com.erick.afiliados.dto.MessageResponseDTO;
import com.erick.afiliados.entity.Product;
import com.erick.afiliados.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final ProductRepository productRepository;

    public MessageService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public MessageResponseDTO generateMessage(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return null;
        }

        Product product = productOptional.get();

        String message = "🚨🔥 PROMOÇÃO IMPERDÍVEL 🔥🚨\n\n"
                + "🛍️ " + product.getName() + "\n\n"
                + "💸 DE: R$ " + product.getOldPrice() + "\n"
                + "🔥 POR: R$ " + product.getPrice() + " 🤑\n\n"
                + "⚡ Corre que pode acabar a qualquer momento!\n\n"
                + "👉 COMPRE AGORA:\n" + product.getAffiliateUrl();

        return new MessageResponseDTO(product.getId(), message);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findAll()
                .stream()
                .filter(Product::getActive)
                .toList();
    }

    public List<Product> getAllActiveProductsOrdered() {
        return productRepository.findByActiveTrueOrderByQueueOrderAsc();
    }

    public String getProductImage(Long id) {
        return productRepository.findById(id)
                .map(Product::getImageUrl)
                .orElse(null);
    }
}