package lk.ijse.service;

import lk.ijse.dto.customer_status;
import lk.ijse.dto.impl.customer_dto;

import java.util.List;

public interface customer_service {
    void saveCustomer(customer_dto customerDto);
    List<customer_dto> getAllCustomer();
    customer_status getCustomer(String customerId);
    void deleteCustomer(String customerId);
    void updateCustomer(String customerId , customer_dto customerDto);
}
