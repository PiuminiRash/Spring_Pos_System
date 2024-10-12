package lk.ijse.controllers;

import lk.ijse.CustomeStatusCodes.SelectedCustomerAndItemAndOrdersErrorStatus;
import lk.ijse.dto.customer_status;
import lk.ijse.dto.impl.customer_dto;
import lk.ijse.exceptions.customer_not_found_exception;
import lk.ijse.service.customer_service;
import lk.ijse.util.app_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.ijse.exceptions.data_persistent_exception;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/customer")
public class customer_controller {
   @Autowired
   private customer_service customerService;

   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
           produces = MediaType.APPLICATION_JSON_VALUE)

   public ResponseEntity<Void> saveCustomer(
           @RequestPart("customerName") String customerName,
           @RequestPart ("customerAddress") String customerEmail
   )

   {
      try {
         //UserId generate
         String userId = app_util.generateCustomerId();

         //Build the Object
         customer_dto buildCustomerDto = new customer_dto();
         buildCustomerDto.setCustomerId(userId);
         buildCustomerDto.setCustomerName(customerName);
         buildCustomerDto.setCustomerEmail(customerEmail);
         customerService.saveCustomer(buildCustomerDto);
         return new ResponseEntity<>(HttpStatus.CREATED);
      }catch (data_persistent_exception e){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
   public customer_status getSelectedCustomer(@PathVariable ("customerId") String customerId){
      if(customerId.isEmpty() || customerId ==null){
         return new SelectedCustomerAndItemAndOrdersErrorStatus(1,"User ID is not valid");
      }
      return customerService.getCustomer(customerId);
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping(value = "/{customerId}")
   public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
      String regexForCustomerId = "^CUS-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
      Pattern regexPattern = Pattern.compile(regexForCustomerId);

      var regexMatcher = regexPattern.matcher(customerId);
      try {
         if (!regexMatcher.matches()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
         customerService.deleteCustomer(customerId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (customer_not_found_exception e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public List<customer_dto> getAllCustomer(){
      return customerService.getAllCustomer();
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @PutMapping(value = "/{customerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public void updateCustomer(
           @RequestPart ("customerName") String customerName,
           @RequestPart ("customerEmail") String customerEmail,
           @PathVariable ("customerId") String customerId
   )

   {
      //Build the Object
      customer_dto buildCustomerDto = new customer_dto();
      buildCustomerDto.setCustomerName(customerName);
      buildCustomerDto.setCustomerEmail(customerEmail);
      customerService.updateCustomer(customerId,buildCustomerDto);
   }

}
