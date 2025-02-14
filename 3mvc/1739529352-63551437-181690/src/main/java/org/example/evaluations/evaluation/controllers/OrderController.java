package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.OrderRequestDto;
import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.services.IOrderService;
import org.example.evaluations.evaluation.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return   orderService.createOrder(  orderRequestDto.getCustomerId(), orderRequestDto.getTotalAmount());
    }
    //Please add APIs here
    @DeleteMapping("/{orderId}")
    public  boolean deleteOrder(@PathVariable UUID orderId){
        return orderService.deleteOrder(orderId);
    }
}