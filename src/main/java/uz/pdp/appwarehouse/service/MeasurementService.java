package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MeasurementDTO;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse addMeasurement(MeasurementDTO measurementDTO) {
        boolean existsByName = measurementRepository.existsByName(measurementDTO.getName());
        if (existsByName) {
            return new ApiResponse("Bunday o'lchov birligi mavjud", false);
        }
        Measurement measurement = new Measurement();
        measurement.setName(measurementDTO.getName());
        measurementRepository.save(measurement);
        return new ApiResponse("O'lchov birligi muvaffaqiyatli qo'shildi", true);
    }

    public ApiResponse getMeasurementById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);
        }
        Measurement measurement = optionalMeasurement.get();
        return new ApiResponse("OK", true, measurement);
    }

    public List<Measurement> getAllMeasurement() {
        return measurementRepository.findAll();
    }

    public ApiResponse editMeasurement(Integer id, MeasurementDTO measurementDTO) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);
        }

        boolean existsByNameNot = measurementRepository.existsByNameNot(measurementDTO.getName());
        if (existsByNameNot) {
            return new ApiResponse("Bunday o'lchov birligi mavjud", false);
        }
        Measurement measurement = optionalMeasurement.get();
        measurement.setName(measurementDTO.getName());
        measurementRepository.save(measurement);
        return new ApiResponse("O'zgartirildi", true, measurement);
    }

    public ApiResponse deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);
        }
        measurementRepository.deleteById(id);
        return new ApiResponse("O'lchov birligi o'chirildi", false);
    }
}
