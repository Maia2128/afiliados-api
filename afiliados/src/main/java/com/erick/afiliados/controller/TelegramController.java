package com.erick.afiliados.controller;

import com.erick.afiliados.service.MessageService;
import com.erick.afiliados.service.MetricsService;
import com.erick.afiliados.service.TelegramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public String test() {
        String msg = """
🔥 OFERTA TOP NA AMAZON

🚴 Bicicleta Aro 29 Rino
💪 Freio a disco + carbono

💸 De: R$ 1.199
🔥 Por: R$ 799

🚨 Aproveita antes que acabe!

👉 Compre aqui:
https://amzn.to/4tm8AKI
""";

        telegramService.sendMessage(msg);
        return "Mensagem enviada!";
    }

    @GetMapping("/send/product/{id}")
    public String sendProductMessage(@PathVariable Long id) {
        var message = messageService.generateMessage(id);

        if (message == null) {
            return "Produto não encontrado";
        }

        String imageUrl = messageService.getProductImage(id);

        if (imageUrl == null || imageUrl.isBlank()) {
            telegramService.sendMessage(message.message());
        } else {
            telegramService.sendPhoto(imageUrl, message.message());
        }

        return "Produto enviado com sucesso!";
    }

    @GetMapping("/send/all")
    public String sendAllProducts() {
        var products = messageService.getAllActiveProducts();

        if (products.isEmpty()) {
            return "Nenhum produto ativo encontrado";
        }

        int enviados = 0;

        for (var product : products) {
            var message = messageService.generateMessage(product.getId());

            if (message != null) {
                String imageUrl = messageService.getProductImage(product.getId());

                if (imageUrl == null || imageUrl.isBlank()) {
                    telegramService.sendMessage(message.message());
                } else {
                    telegramService.sendPhoto(imageUrl, message.message());
                }

                enviados++;
            }
        }

        return enviados + " produto(s) enviado(s) com sucesso!";
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
            telegramService.sendMessage(messageResponse.message());
        } else {
            telegramService.sendPhoto(imageUrl, messageResponse.message());
        }

        return "Top produto enviado com sucesso!";
    }
}