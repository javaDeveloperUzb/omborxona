package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.service.InputProductService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;
    
    @PostMapping
    public HttpEntity<?> addInputProduct(@RequestBody InputProductDTO inputProductDTO) {
        ApiResponse apiResponse = inputProductService.addInputProduct(inputProductDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getInputProductById(@PathVariable Integer id) {
        ApiResponse apiResponse = inputProductService.getInputProductById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<InputProduct> getAllInputProduct() {
        return inputProductService.getAllInputProduct();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editInputProduct(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDTO) {
        ApiResponse apiResponse = inputProductService.editInputProduct(id, inputProductDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInputProduct(@PathVariable Integer id) {
        ApiResponse apiResponse = inputProductService.deleteInputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}