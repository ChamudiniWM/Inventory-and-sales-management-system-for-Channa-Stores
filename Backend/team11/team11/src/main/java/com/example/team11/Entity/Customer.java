package com.example.team11.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL) // Using CascadeType.ALL instead of REMOVE
    @MapsId
    @JoinColumn(name = "id") // FK to `users.id`
    private User user;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
