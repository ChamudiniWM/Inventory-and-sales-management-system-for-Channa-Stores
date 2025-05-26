package com.example.team11.Controller;

import com.example.team11.DTO.CartItemDTO;
import com.example.team11.Service.CartItemService;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/{cartId}/items")
public class CartItemController {
    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Add a CartItem
    @PostMapping
    public ResponseEntity<String> addCartItem(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
        try {
            cartItemService.addCartItem(cartId, cartItemDTO); // Delegates to CartItemService
            return ResponseEntity.ok("Cart item added successfully!");
        } catch (Exception e) {
            logger.error("Error adding cart item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding cart item");
        }
    }

    // Get all CartItems for a Cart
    @GetMapping("/items")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable Long cartId) {
        try {
            List<CartItemDTO> cartItems = cartItemService.getCartItems(cartId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            logger.error("Error fetching cart items", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete a CartItem
    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.deleteCartItem(cartId, itemId); // Delegates to CartItemService
            return ResponseEntity.ok("Cart item deleted successfully!");
        } catch (Exception e) {
            logger.error("Error deleting cart item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting cart item");
        }
    }

    // Update the quantity of a CartItem
    // Update the quantity of a CartItem
@PatchMapping("/{itemId}/quantity")
public ResponseEntity<String> updateCartItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody Map<String, Integer> request) {
    try {
        if (!request.containsKey("quantity")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing 'quantity' field");
        }
        int newQuantity = request.get("quantity");
        cartItemService.updateCartItemQuantity(cartId, itemId, newQuantity);
        return ResponseEntity.ok("Cart item quantity updated successfully!");
    } catch (Exception e) {
        logger.error("Error updating cart item quantity", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating cart item quantity");
    }
}
}
