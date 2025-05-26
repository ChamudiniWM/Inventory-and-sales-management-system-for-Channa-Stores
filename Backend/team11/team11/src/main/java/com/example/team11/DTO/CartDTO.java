package com.example.team11.DTO;

import java.util.List;

public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;  // List of CartItemDTO objects
    private Double totalPrice;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItemDTO> getItems() { return items; }
    public void setItems(List<CartItemDTO> items) { this.items = items; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    

}