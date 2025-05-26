package com.example.team11.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cartitem")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long productid;
    private String productName;
    private Integer quantity;
    private Double price;

    // Define the ManyToOne relationship with Cart
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false) // This creates the foreign key column "cart_id"
    private Cart cart;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
