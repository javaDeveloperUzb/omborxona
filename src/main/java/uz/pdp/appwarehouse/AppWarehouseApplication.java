package uz.pdp.appwarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.appwarehouse.repository.SupplierRepository;

@SpringBootApplication
public class AppWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWarehouseApplication.class, args);
    }

}
