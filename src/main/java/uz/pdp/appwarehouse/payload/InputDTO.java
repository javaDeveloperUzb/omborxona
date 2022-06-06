package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDTO {
    private Timestamp data;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
}
