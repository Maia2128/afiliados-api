package com.erick.afiliados.controller;

import com.erick.afiliados.service.TelegramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    private final TelegramService telegramService;

    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    // 🔥 TESTE DIRETO (SEM BANCO)
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
}