package lk.ijse.dao;

import lk.ijse.entity.impl.item_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface item_dao extends JpaRepository<item_entity, String> {
}
