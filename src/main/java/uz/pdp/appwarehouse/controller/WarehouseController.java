package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.repository.WarehouseRepository;
import uz.pdp.appwarehouse.service.InputProductService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping
    public List<Warehouse> getAllInputProduct() {
        return warehouseRepository.findAll();
    }
}
