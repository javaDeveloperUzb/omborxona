package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;
    
    @PostMapping
    public HttpEntity<?> addInput(@RequestBody InputDTO inputDTO) {
        ApiResponse apiResponse = inputService.addInput(inputDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getInputById(@PathVariable Integer id) {
        ApiResponse apiResponse = inputService.getInputById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Input> getAllInput() {
        return inputService.getAllInput();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editInput(@PathVariable Integer id, @RequestBody InputDTO inputDTO) {
        ApiResponse apiResponse = inputService.editInput(id, inputDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInput(@PathVariable Integer id) {
        ApiResponse apiResponse = inputService.deleteInput(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}