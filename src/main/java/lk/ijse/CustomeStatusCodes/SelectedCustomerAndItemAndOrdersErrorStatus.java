package lk.ijse.CustomeStatusCodes;

import lk.ijse.dto.customer_status;
import lk.ijse.dto.item_status;
import lk.ijse.dto.order_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedCustomerAndItemAndOrdersErrorStatus implements customer_status, item_status , order_status {
    private int statusCode;
    private String status;
}