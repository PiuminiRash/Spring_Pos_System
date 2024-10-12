package lk.ijse.entity.impl;

import jakarta.persistence.*;
import lk.ijse.entity.super_entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class customer_entity implements super_entity{
    @Id
    private String cus_id;
    private String cus_name;
    private String cus_email;

    @OneToMany(mappedBy = "customer")
    private List<order_entity> customerId;
}
