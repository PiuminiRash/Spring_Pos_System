package lk.ijse.dao;

import lk.ijse.entity.impl.customer_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customer_dao extends JpaRepository<customer_entity,String> {
}
