package lk.ijse.service;

import lk.ijse.dto.impl.item_dto;
import lk.ijse.dto.item_status;

import java.util.List;

public interface item_service {
    void saveItem(item_dto itemDto);
    List<item_dto> getAllItem();
    item_status getItem(String itemCode);
    void deleteItem(String itemCode);
    void updateItem(String itemCode , item_dto itemDto);
}
