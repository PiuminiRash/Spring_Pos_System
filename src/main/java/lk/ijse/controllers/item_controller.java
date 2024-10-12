package lk.ijse.controllers;

import lk.ijse.CustomeStatusCodes.SelectedCustomerAndItemAndOrdersErrorStatus;
import lk.ijse.dto.impl.item_dto;
import lk.ijse.dto.item_status;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.exceptions.item_not_found_exception;
import lk.ijse.service.item_service;
import lk.ijse.util.app_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/item")
public class item_controller {
    @Autowired
    private item_service itemService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveItem(
            @RequestPart("ItemName") String itemName,
            @RequestPart ("ItemPrice") String itemPrice,
            @RequestPart ("ItemQty") String itemQty
    )

    {
        try {
            //UserId generate
            String itemId = app_util.generateItemCode();

            //Build the Object
            item_dto buildItemDto = new item_dto();
            buildItemDto.setItemCode(itemId);
            buildItemDto.setItemName(itemName);
            buildItemDto.setItemPrice(itemPrice);
            buildItemDto.setItemQty(itemQty);
            itemService.saveItem(buildItemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (data_persistent_exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public item_status getSelectedItem(@PathVariable("itemCode") String itemCode){
        if(itemCode.isEmpty() || itemCode == null){
            return new SelectedCustomerAndItemAndOrdersErrorStatus(1,"Item code is not valid");
        }
        return itemService.getItem(itemCode);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemCode") String itemCode){
        String regexForItemCode = "^ITEM-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForItemCode);

        var regexMatcher = regexPattern.matcher(itemCode);
        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (item_not_found_exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<item_dto> getAllItem(){
        return itemService.getAllItem();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{itemCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCustomer(
            @RequestPart ("itemName") String itemName,
            @RequestPart ("itemPrice") String itemPrice,
            @RequestPart ("itemQty") String itemQty,
            @PathVariable ("itemCode") String itemCode
    )

    {
        //Build the Object
        item_dto buildItemDto = new item_dto();
        buildItemDto.setItemName(itemName);
        buildItemDto.setItemPrice(itemPrice);
        buildItemDto.setItemQty(itemQty);
        itemService.updateItem(itemCode,buildItemDto);
    }
}
