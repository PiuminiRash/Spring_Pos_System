package lk.ijse.service;

import lk.ijse.dto.impl.order_dto;
import lk.ijse.dto.order_status;

import java.util.List;

public interface orders_service {
    void saveOrder(order_dto orderDto);
    List<order_dto> getAllOrder();
}
