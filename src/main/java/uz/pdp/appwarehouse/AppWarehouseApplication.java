package uz.pdp.appwarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class AppWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWarehouseApplication.class, args);


        Random random = new Random();

        System.out.println(random.nextInt(50));
//        Stack - LIFO oxirgi kirgan birinchi da int a = 10 desak a= 10 saqlanadi, obekt bolsa nomi saqlandi
//        heap = obekt saqlanadihr
        

    }

}
