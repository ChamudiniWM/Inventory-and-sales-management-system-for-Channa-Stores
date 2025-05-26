package com.example.team11.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team11.DTO.OrderDTO;
import com.example.team11.Entity.Order;
import com.example.team11.Entity.Product;
import com.example.team11.Repository.OrderRepository;
import com.example.team11.Repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Fetch all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Fetch a specific order by ID
    public OrderDTO getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::convertToDTO).orElse(null);
    }

    // Create a new order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());
        Order order = new Order();
        order.setProducts(products);
        order.setTotal(calculateTotal(products));
        order.setStatus("New");
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    // Update the status of an order
    public boolean updateOrderStatus(int id, String status) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setStatus(status);
            orderRepository.save(updatedOrder);
            return true;
        }
        return false;
    }

    // Conversion methods
    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setProductIds(order.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());
        return dto;
    }

    private double calculateTotal(List<Product> products) {
        return products.stream().mapToDouble(product -> product.getPrice()).sum();
    }
}
