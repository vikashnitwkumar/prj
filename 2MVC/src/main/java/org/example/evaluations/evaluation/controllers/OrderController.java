package org.example.evaluations.evaluation.controllers;

import java.util.List;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    //Please implement GET Apis with path `/order/{orderId}` and `/order` here

    @GetMapping("/order/{orderId}")
    public Order getOrderById( @PathVariable long orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/order")
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }
    
}
