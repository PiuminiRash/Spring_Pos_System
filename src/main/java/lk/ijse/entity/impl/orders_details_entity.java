package lk.ijse.entity.impl;

import jakarta.persistence.*;
import lk.ijse.entity.super_entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class orders_details_entity implements super_entity {
    @Id
    private String od_id;
    private String itemQty;

    @ManyToOne
    @JoinColumn(name = "itemId",nullable = false)
    private item_entity item;

    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private order_entity orders;
}
