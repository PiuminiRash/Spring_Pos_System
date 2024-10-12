package lk.ijse.entity.impl;

import jakarta.persistence.*;
import lk.ijse.entity.super_entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class order_entity implements super_entity {
    @Id
    private String order_id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customerId",nullable = false)
    private customer_entity customer;

    @OneToMany(mappedBy = "orders")
    private List<orders_details_entity> orderId;
}
