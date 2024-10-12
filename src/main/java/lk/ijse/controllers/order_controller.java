package lk.ijse.controllers;

import lk.ijse.dto.impl.order_dto;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.service.orders_service;
import lk.ijse.util.app_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class order_controller {
    @Autowired
    private orders_service ordersService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveOrder(
            @RequestPart("Date") Date date,
            @RequestPart ("customerId") String customerId
    )

    {
        try {
            //OrderId generate
            String orderId = app_util.generateOrderId();

            //Build the Object
            order_dto buildOrderDto = new order_dto();
            buildOrderDto.setOrderId(orderId);
            buildOrderDto.setDate(date);
            buildOrderDto.setCustomerId(customerId);
            ordersService.saveOrder(buildOrderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (data_persistent_exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<order_dto> getAllOrder(){
        return ordersService.getAllOrder();
    }
}
