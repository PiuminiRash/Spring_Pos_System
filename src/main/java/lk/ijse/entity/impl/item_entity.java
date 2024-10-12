package lk.ijse.entity.impl;

import jakarta.persistence.*;
import lk.ijse.entity.super_entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class item_entity implements super_entity {
    @Id
    private String itemCode;
    private String itemName;
    private String itemPrice;
    private String itemQty;

    @OneToMany(mappedBy = "item")
    private List<orders_details_entity> itemId;
}
