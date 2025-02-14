package org.example.evaluations.evaluation.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order extends BaseModel {
    private OrderStatus status;
    private Long customerId;
    private Double totalAmount;
}
