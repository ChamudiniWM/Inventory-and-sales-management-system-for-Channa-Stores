package com.example.team11.Service;

import com.example.team11.DTO.CartItemDTO;
import com.example.team11.Entity.Cart;
import com.example.team11.Entity.CartItem;
import com.example.team11.Repository.CartItemRepository;
import com.example.team11.Repository.CartRepository;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void addCartItem(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        
        // Create and populate the CartItem entity from the DTO
        CartItem cartItem = new CartItem();
        cartItem.setProductName(cartItemDTO.getProductName());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPrice(cartItemDTO.getPrice());
        cartItem.setCart(cart);  // Associate with the Cart
    
        // Save the CartItem
        cartItemRepository.save(cartItem);  // Save the CartItem explicitly
    
        // Add the item to the Cart
        cart.getItems().add(cartItem);
    
        // Update the cart's total price
        updateTotalPrice(cart);
        
        // Save the updated cart
        cartRepository.save(cart);
    }

    public List<CartItemDTO> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems().stream()
                .map(cartItem -> new CartItemDTO(cartItem.getId(), cartItem.getProductName(), cartItem.getQuantity(), cartItem.getPrice()))
                .collect(Collectors.toList());
    }

    public void deleteCartItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().removeIf(item -> item.getId().equals(itemId)); // Remove item by ID
        
        // Update total price
        updateTotalPrice(cart);

        // Save the updated cart
        cartRepository.save(cart);
    }

    public void updateCartItemQuantity(Long cartId, Long itemId, int newQuantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItem.setQuantity(newQuantity);

        // Update total price
        updateTotalPrice(cart);

        // Save the updated cart
        cartRepository.save(cart);
    }

    private void updateTotalPrice(Cart cart) {
        double totalPrice = cart.getItems().stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
        cart.setTotalPrice(totalPrice);
    }
}