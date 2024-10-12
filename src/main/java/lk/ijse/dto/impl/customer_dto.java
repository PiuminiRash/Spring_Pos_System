package lk.ijse.dto.impl;

import lk.ijse.dto.customer_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class customer_dto implements customer_status {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private List<order_dto> orderId;
}
