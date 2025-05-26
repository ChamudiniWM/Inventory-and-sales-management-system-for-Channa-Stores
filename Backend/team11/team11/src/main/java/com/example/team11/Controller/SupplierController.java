package com.example.team11.Controller;

import com.example.team11.DTO.SupplierDTO;
import com.example.team11.Entity.Supplier;
import com.example.team11.Service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Map<String, Object> requestBody) {
        Long userId = Long.valueOf(requestBody.get("userId").toString());
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setCompany(requestBody.get("company").toString());
        supplierDTO.setAddress(requestBody.get("address").toString());
        supplierDTO.setPhoneNumber(requestBody.get("phoneNumber").toString());

        Supplier createdSupplier = supplierService.createSupplier(userId, supplierDTO);
        return ResponseEntity.ok(createdSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) throws Exception {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) throws Exception {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
