package lk.ijse.dto.impl;

import lk.ijse.dto.order_details_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class order_details_dto implements order_details_status {
    private String orderId;
    private String ItemCode;
}
