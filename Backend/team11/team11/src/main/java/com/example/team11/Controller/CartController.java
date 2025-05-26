package com.example.team11.Controller;

import com.example.team11.DTO.CartDTO;
import com.example.team11.Service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create a new Cart
    @PostMapping("/create")
    public CartDTO createCart() {
        return cartService.createCart();
    }

    // Delete a Cart by ID
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

// Get a Cart by ID with its items
@GetMapping("/{id}")
public CartDTO getCart(@PathVariable Long id) {
    return cartService.getCartById(id); // Delegates to CartService
}


}
