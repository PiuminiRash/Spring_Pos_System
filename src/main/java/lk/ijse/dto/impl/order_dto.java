package lk.ijse.dto.impl;

import lk.ijse.dto.order_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class order_dto implements order_status {
    private String orderId;
    private Date date;
    private String customerId;
}
