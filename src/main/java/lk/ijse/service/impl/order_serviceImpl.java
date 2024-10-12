package lk.ijse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.dao.order_dao;
import lk.ijse.dto.impl.order_dto;
import lk.ijse.entity.impl.order_entity;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.service.orders_service;
import lk.ijse.util.mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class order_serviceImpl implements orders_service {
    @Autowired
    private mapping mapping;

    @Autowired
    private order_dao orderDao;

    @Override
    public void saveOrder(order_dto orderDto) {
        order_entity savedOrder =
                orderDao.save((mapping.toOrdersEntity(orderDto)));
        if (savedOrder == null) {
            throw new data_persistent_exception("Orders Not Saved...!!! ");
        }
    }

    @Override
    public List<order_dto> getAllOrder() {
        List<order_entity> allOrder = orderDao.findAll();
        return mapping.asOrdersDtoList(allOrder);
    }
}
