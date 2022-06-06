package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;
    
    @PostMapping
    public HttpEntity<?> addOutput(@RequestBody OutputProductDTO outputProductDTO) {
        ApiResponse apiResponse = outputProductService.addOutputProduct(outputProductDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOutputProductById(@PathVariable Integer id) {
        ApiResponse apiResponse = outputProductService.getOutputProductById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<OutputProduct> getAllOutputProduct() {
        return outputProductService.getAllOutputProduct();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDTO) {
        ApiResponse apiResponse = outputProductService.editOutputProduct(id, outputProductDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOutputProduct(@PathVariable Integer id) {
        ApiResponse apiResponse = outputProductService.deleteOutputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}