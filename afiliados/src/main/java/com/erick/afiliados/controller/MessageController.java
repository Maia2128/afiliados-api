package com.erick.afiliados.controller;

import com.erick.afiliados.dto.MessageResponseDTO;
import com.erick.afiliados.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/product/{id}")
    public MessageResponseDTO generate(@PathVariable Long id) {
        return service.generateMessage(id);
    }
}