package com.erick.afiliados.service;

import com.erick.afiliados.entity.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    private final MessageService messageService;
    private final TelegramService telegramService;

    private int currentIndex = 0;

    public SchedulerService(MessageService messageService, TelegramService telegramService) {
        this.messageService = messageService;
        this.telegramService = telegramService;
    }

    @Scheduled(fixedRate = 900000) // 10 minutos
    public void sendNextOffer() {
        List<Product> products = messageService.getAllActiveProductsOrdered();

        if (products.isEmpty()) {
            System.out.println("Nenhum produto ativo para enviar.");
            return;
        }

        if (currentIndex >= products.size()) {
            currentIndex = 0;
        }

        Product product = products.get(currentIndex);
        var message = messageService.generateMessage(product.getId());

        if (message != null) {
            String imageUrl = messageService.getProductImage(product.getId());

            if (imageUrl != null && !imageUrl.isBlank()) {
                telegramService.sendPhoto(imageUrl, message.message());
            } else {
                telegramService.sendMessage(message.message());
            }
        }

        currentIndex++;
    }
}