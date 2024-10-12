package lk.ijse.controllers;

import lk.ijse.dto.impl.order_details_dto;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.service.order_details_service;
import lk.ijse.util.app_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order_details")
public class order_details_controller {
    @Autowired
    private order_details_service orderDetailsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveOrderDetails(
            @RequestPart("orderId") String orderId,
            @RequestPart ("itemId") String itemCode
    )

    {
        try {
            //OrderId generate
            String odId = app_util.generateOrderDetailsId();

            //Build the Object
            order_details_dto buildOrderDetailsDto = new order_details_dto();
            buildOrderDetailsDto.setOrderId(orderId);
            buildOrderDetailsDto.setItemCode(itemCode);
            orderDetailsService.saveOrderDetails(buildOrderDetailsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (data_persistent_exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<order_details_dto> getAllOrderDetails(){
        return orderDetailsService.getAllOrderDetails();
    }
}
