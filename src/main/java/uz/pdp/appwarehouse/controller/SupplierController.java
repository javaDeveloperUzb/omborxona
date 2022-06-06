package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public HttpEntity<?> addSupplier(@RequestBody SupplierDTO supplierDTO) {
        ApiResponse apiResponse = supplierService.addSupplier(supplierDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSupplierById(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.getSupplierById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Supplier> getAllSupplier() {
        return supplierService.getAllSupplier();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        ApiResponse apiResponse = supplierService.editSupplier(id, supplierDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSupplier(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.deleteSupplier(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}