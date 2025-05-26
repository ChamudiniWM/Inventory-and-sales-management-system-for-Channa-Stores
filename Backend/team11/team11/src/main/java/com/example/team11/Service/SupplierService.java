package com.example.team11.Service;

import com.example.team11.DTO.SupplierDTO;
import com.example.team11.Entity.Supplier;
import com.example.team11.Entity.User;
import com.example.team11.Repository.SupplierRepository;
import com.example.team11.Repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    public Supplier createSupplier(Long userId, SupplierDTO supplierDTO) {
        // Check if the user exists
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        
        // Check if the user is already a supplier
        Optional<Supplier> existingSupplier = supplierRepository.findById(userId);
        if (existingSupplier.isPresent()) {
            throw new RuntimeException("User with ID " + userId + " is already a supplier");
        }
        
        // Map SupplierDTO to Supplier Entity
        Supplier supplier = new Supplier();
        supplier.setUser(user);  // Set the existing user as the supplier's user
        supplier.setCompany(supplierDTO.getCompany());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        
        // Save supplier to the database
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        // Find the supplier by ID, throws exception if not found
        return supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier with ID " + id + " not found"));
    }

    public List<Supplier> getAllSuppliers() {
        // Return all suppliers
        return supplierRepository.findAll();
    }

    @Transactional
    public Supplier updateSupplier(Long id, SupplierDTO supplierDTO) throws Exception {
        // Fetch supplier by ID
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new Exception("Supplier with ID " + id + " not found"));

        // Update supplier-specific fields
        supplier.setCompany(supplierDTO.getCompany());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier);

        // Fetch the related user (assuming a Supplier has a related User)
        User user = supplier.getUser(); // Assuming there is a `getUser()` method in Supplier
        if (user != null) {
            // Update user-related fields (e.g., email, username)
            user.setEmail(supplierDTO.getEmail());
            user.setPassword(supplierDTO.getPassword());
            user.setUsername(supplierDTO.getUsername());
            userRepository.save(user);
        }

        return supplier; // Return the updated supplier
    }

    public void deleteSupplier(Long id) throws Exception {
        // Validate if supplier exists
        Supplier supplierToDelete = supplierRepository.findById(id)
            .orElseThrow(() -> new Exception("Supplier with ID " + id + " not found"));

        // Delete supplier
        supplierRepository.delete(supplierToDelete);
    }
}
