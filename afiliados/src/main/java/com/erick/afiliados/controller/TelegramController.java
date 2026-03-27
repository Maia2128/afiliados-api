package com.erick.afiliados.controller;

import com.erick.afiliados.service.MessageService;
import com.erick.afiliados.service.MetricsService;
import com.erick.afiliados.service.TelegramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    private final TelegramService telegramService;
    private final MessageService messageService;
    private final MetricsService metricsService;

    public TelegramController(TelegramService telegramService,
                              MessageService messageService,
                              MetricsService metricsService) {
        this.telegramService = telegramService;
        this.messageService = messageService;
        this.metricsService = metricsService;
    }

    @GetMapping("/send/product/{id}")
    public String sendProductMessage(@PathVariable Long id) {
        var messageResponse = messageService.generateMessage(id);

        if (messageResponse == null) {
            return "Produto não encontrado";
        }

        String imageUrl = messageService.getProductImage(id);

        if (imageUrl == null || imageUrl.isBlank()) {
            return telegramService.sendMessage(messageResponse.message());
        }

        return telegramService.sendPhoto(imageUrl, messageResponse.message());
    }

    @GetMapping("/send/all")
    public String sendAllProducts() {
        var products = messageService.getAllActiveProducts();

        if (products.isEmpty()) {
            return "Nenhum produto ativo encontrado";
        }

        for (var product : products) {
            var message = messageService.generateMessage(product.getId());

            if (message != null) {
                String imageUrl = messageService.getProductImage(product.getId());

                if (imageUrl == null || imageUrl.isBlank()) {
                    telegramService.sendMessage(message.message());
                } else {
                    telegramService.sendPhoto(imageUrl, message.message());
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Mensagens enviadas com sucesso";
    }

    @GetMapping("/send/top")
    public String sendTopProduct() {
        var topProduct = metricsService.getTopProduct();

        if (topProduct == null) {
            return "Nenhum produto encontrado";
        }

        var messageResponse = messageService.generateMessage(topProduct.getProductId());

        if (messageResponse == null) {
            return "Produto não encontrado";
        }

        String imageUrl = messageService.getProductImage(topProduct.getProductId());

        if (imageUrl == null || imageUrl.isBlank()) {
            return telegramService.sendMessage(messageResponse.message());
        }

        return telegramService.sendPhoto(imageUrl, messageResponse.message());
    }
}