package lk.ijse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.dao.order_details_dao;
import lk.ijse.dto.impl.order_details_dto;
import lk.ijse.entity.impl.orders_details_entity;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.service.order_details_service;
import lk.ijse.util.mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class order_details_serviceImpl implements order_details_service {
    @Autowired
    private mapping mapping;

    @Autowired
    private order_details_dao orderDetailsDao;

    @Override
    public void saveOrderDetails(order_details_dto orderDetailsDto) {
        orders_details_entity savedOrderDetails =
                orderDetailsDao.save((mapping.toOrdersDetailsEntity(orderDetailsDto)));
        if (savedOrderDetails == null) {
            throw new data_persistent_exception("Orders Not Saved...!!! ");
        }
    }

    @Override
    public List<order_details_dto> getAllOrderDetails() {
        List<orders_details_entity> allOrderDetails = orderDetailsDao.findAll();
        return mapping.asOrdersDetailsDtoList(allOrderDetails);
    }
}
