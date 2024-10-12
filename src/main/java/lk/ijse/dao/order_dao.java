package lk.ijse.dao;

import lk.ijse.entity.impl.order_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface order_dao extends JpaRepository<order_entity, String> {
}
