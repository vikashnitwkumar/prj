package org.example.evaluations.evaluation.repositories;

import org.example.evaluations.evaluation.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
    private Map<Long, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }

    public Order findById(Long orderId) {
        //Please add logic for getting Order corresponding to particular
        // orderId from HashMap where orderId is stored as key and Order is stored
        //value in HashMap

        return orders.getOrDefault(orderId, null);
    }

    public List<Order> findAll() {
        //Please add logic for getting Orders from HashMap
        return  List.copyOf(orders.values());
    }

    //Please don't delete below function
    public Order save(Order order) {
        orders.put(order.getId(),order);
        return orders.get(order.getId());
    }
}
