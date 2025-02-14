package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(Long orderId) {
       return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
