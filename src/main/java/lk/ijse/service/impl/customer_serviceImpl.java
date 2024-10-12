package lk.ijse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CustomeStatusCodes.SelectedCustomerAndItemAndOrdersErrorStatus;
import lk.ijse.dao.customer_dao;
import lk.ijse.dto.customer_status;
import lk.ijse.dto.impl.customer_dto;
import lk.ijse.entity.impl.customer_entity;
import lk.ijse.exceptions.customer_not_found_exception;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.service.customer_service;
import lk.ijse.util.mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class customer_serviceImpl implements customer_service {
    @Autowired
    private mapping mapping;

    @Autowired
    private customer_dao customerDao;

    @Override
    public void saveCustomer(customer_dto customerDto) {
        customer_entity savedCustomer =
                customerDao.save((mapping.toCustomerEntity(customerDto)));
        if (savedCustomer==null) {
            throw new data_persistent_exception("Customer Not Saved...!!! ");
        }
    }

    @Override
    public List<customer_dto> getAllCustomer() {
        List<customer_entity> allCustomer = customerDao.findAll();
        return mapping.asCustomerDtoList(allCustomer);
    }

    @Override
    public customer_status getCustomer(String customerId) {
        if (customerDao.existsById(customerId)) {
            customer_entity selectedCustomer = customerDao.getReferenceById(customerId);
            return mapping.toCustomerDto(selectedCustomer);
        } else {
            return new SelectedCustomerAndItemAndOrdersErrorStatus(2 , "Customer with id " + customerId + " not found...!!!");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<customer_entity> existCustomer = customerDao.findById(customerId);
        if (!existCustomer.isPresent()) {
            throw new customer_not_found_exception("customer with id " + customerId + " not found ");
        } else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerId, customer_dto customerDto) {
        Optional<customer_entity> tmpCustomer = customerDao.findById(customerId);
        if (tmpCustomer.isPresent()) {
            tmpCustomer.get().setCus_name(customerDto.getCustomerName());
            tmpCustomer.get().setCus_email(customerDto.getCustomerEmail());
        }
    }
}
