package lk.ijse.service.impl;

import lk.ijse.CustomeStatusCodes.SelectedCustomerAndItemAndOrdersErrorStatus;
import lk.ijse.dao.item_dao;
import lk.ijse.dto.impl.item_dto;
import lk.ijse.dto.item_status;
import lk.ijse.entity.impl.item_entity;
import lk.ijse.exceptions.data_persistent_exception;
import lk.ijse.exceptions.item_not_found_exception;
import lk.ijse.service.item_service;
import lk.ijse.util.mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/items")
public class item_serviceImpl implements item_service {
    @Autowired
    private mapping mapping;

    @Autowired
    private item_dao itemDao;

    @Override
    public void saveItem(item_dto itemDto) {
        item_entity savedItem =
                itemDao.save((mapping.toItemEntity(itemDto)));
        if (savedItem == null) {
            throw new data_persistent_exception("Item Not Saved...!!! ");
        }
    }

    @Override
    public List<item_dto> getAllItem() {
        List<item_entity> allItem = itemDao.findAll();
        return mapping.asItemDtoList(allItem);
    }

    @Override
    public item_status getItem(String itemCode) {
        if (itemDao.existsById(itemCode)) {
            item_entity selectedItem = itemDao.getReferenceById(itemCode);
            return mapping.toItemDto(selectedItem);
        } else {
            return new SelectedCustomerAndItemAndOrdersErrorStatus(2 , "Item with code " + itemCode + " not found...!!!");
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<item_entity> existItem = itemDao.findById(itemCode);
        if (!existItem.isPresent()) {
            throw new item_not_found_exception("customer with id " + itemCode + " not found ");
        } else {
            itemDao.deleteById(itemCode);
        }
    }

    @Override
    public void updateItem(String itemCode, item_dto itemDto) {
        Optional<item_entity> tmpItem = itemDao.findById(itemCode);
        if (tmpItem.isPresent()) {
            tmpItem.get().setItemCode(itemDto.getItemCode());
            tmpItem.get().setItemName(itemDto.getItemName());
            tmpItem.get().setItemPrice(itemDto.getItemPrice());
            tmpItem.get().setItemQty(itemDto.getItemQty());
        }
    }
}
