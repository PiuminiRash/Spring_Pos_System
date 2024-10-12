package lk.ijse.dao;

import lk.ijse.entity.impl.orders_details_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface order_details_dao extends JpaRepository<orders_details_entity, String> {
}
