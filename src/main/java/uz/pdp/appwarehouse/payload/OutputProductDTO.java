package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputProductDTO {
    private Integer productId;

    private Timestamp data;

    private Double amount;

    private Integer currencyId;

    private Double price;
}
