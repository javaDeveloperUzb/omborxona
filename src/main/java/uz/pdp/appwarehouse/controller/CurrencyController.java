package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CurrencyDTO;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public HttpEntity<?> addCurrency(@RequestBody CurrencyDTO currencyDTO) {
        ApiResponse apiResponse = currencyService.addCurrency(currencyDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getCurrencyById(@PathVariable Integer id) {
        ApiResponse apiResponse = currencyService.getCurrencyById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Currency> getAllCurrency() {
        return currencyService.getAllCurrency();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCurrency(@PathVariable Integer id, @RequestBody CurrencyDTO currencyDTO) {
        ApiResponse apiResponse = currencyService.editCurrency(id, currencyDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCurrency(@PathVariable Integer id) {
        ApiResponse apiResponse = currencyService.deleteCurrency(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}