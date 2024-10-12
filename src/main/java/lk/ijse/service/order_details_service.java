package lk.ijse.service;

import lk.ijse.dto.impl.order_details_dto;

import java.util.List;

public interface order_details_service {
    void saveOrderDetails(order_details_dto orderDetailsDto);
    List<order_details_dto> getAllOrderDetails();
}
