package com.example.team11.Controller;

import com.example.team11.DTO.CustomerDTO;
import com.example.team11.Entity.Customer;
import com.example.team11.Service.CustomerService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
        @RequestBody Map<String, Object> requestBody) {
    
        Long userId = Long.valueOf(requestBody.get("userId").toString());
        CustomerDTO customerDTO = new CustomerDTO();
    
        // Handle null for phoneNumber
        Object phoneNumberObj = requestBody.get("phoneNumber");
        customerDTO.setPhoneNumber(phoneNumberObj != null ? phoneNumberObj.toString() : null);
    
        Customer createdCustomer = customerService.createCustomer(userId, customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws Exception {
        Customer customer = customerService.getCustomerById(id); 

        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers(); 

        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Validated @RequestBody CustomerDTO customerDTO) throws Exception {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();

    }

}
