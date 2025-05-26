package com.example.team11.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team11.DTO.ProductDTO;
import com.example.team11.Entity.Product;
import com.example.team11.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(int id) {
        return productRepository.findById((long) id).map(this::convertToDTO).orElse(null);
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
    }

    public boolean updateProduct(ProductDTO productDTO) {
        if (productRepository.existsById((long) productDTO.getId())) {
            Product product = convertToEntity(productDTO);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById((long) id)) {
            productRepository.deleteById((long) id);
            return true;
        }
        return false;
    }

    // Conversion methods
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        return product;
    }


    
}
