package com.erick.afiliados.controller;

import com.erick.afiliados.service.RedirectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redirect")
public class RedirectController {

    private final RedirectService service;

    public RedirectController(RedirectService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Void> redirect(@PathVariable Long productId) {
        String url = service.registerClickAndGetUrl(productId);

        if (url == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(302)
                .header(HttpHeaders.LOCATION, url)
                .build();
    }
}