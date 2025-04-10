package com.app.e_commerc.components;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.e_commerc.services.CartService;

@Component
public class CartCleaner {

    private final CartService cartService;

    public CartCleaner(CartService cartService) {
        this.cartService = cartService;
    }

    // Ejecutar cada minuto
    @Scheduled(fixedRate = 60000)
    public void clean() {
        cartService.cleanInactiveCarts();
    }
}