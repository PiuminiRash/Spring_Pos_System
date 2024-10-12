package lk.ijse.dto.impl;

import lk.ijse.dto.item_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class item_dto implements item_status {
    private String itemCode;
    private String itemName;
    private String itemPrice;
    private String itemQty;
}
