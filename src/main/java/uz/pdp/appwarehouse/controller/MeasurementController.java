package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MeasurementDTO;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    //O'LCHOV BIRLIGINI KIRITISH
    @PostMapping
    public HttpEntity<?> addMeasurement(@RequestBody MeasurementDTO measurementDTO) {
        ApiResponse apiResponse = measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    //BITTA O'LCHOV BIRLIGINI CHAQIRISH
    @GetMapping("/{id}")
    public HttpEntity<?> getMeasurementById(@PathVariable Integer id) {
        ApiResponse apiResponse = measurementService.getMeasurementById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //BARCHA O'LCHOV BIRLIGINI CHAQIRISH
    @GetMapping
    public List<Measurement> getAllMeasurement() {
        return measurementService.getAllMeasurement();
    }

    //O'LCHOV BIRLIGINI O'ZGARTIRISH
    @PutMapping("/{id}")
    public HttpEntity<?> editMeasurement(@PathVariable Integer id, @RequestBody MeasurementDTO measurementDTO) {
        ApiResponse apiResponse = measurementService.editMeasurement(id, measurementDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    //O'LCHOV BIRLIGINI O'CHIRISH
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMeasurement(@PathVariable Integer id) {
        ApiResponse apiResponse = measurementService.deleteMeasurement(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}