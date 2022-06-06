package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputServiceImpl implements InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public ApiResponse addInput(InputDTO inputDTO) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse("Bunday ta'minotchi mavjud emas", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Bunday valyuta turi mavjud emas", false);
        }

        boolean existsByFactureNumber = inputRepository.existsByFactureNumber(inputDTO.getFactureNumber());
        if (existsByFactureNumber) {
            return new ApiResponse("Bunday faktura raqam oldindan qo'shilgan", false);
        }
        
        Input input = new Input();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        input.setDate(timestamp);
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDTO.getFactureNumber());
        inputRepository.save(input);
        return new ApiResponse("Kirim saqlandi", true);
    }

    @Override
    public ApiResponse getInputById(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.map(input -> new ApiResponse("Ok", true, input)).orElseGet(() -> new ApiResponse("Kirim topilmadi", false));
    }

    @Override
    public List<Input> getAllInput() {
        return inputRepository.findAll();
    }

    @Override
    public ApiResponse editInput(Integer id, InputDTO inputDTO) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new ApiResponse("Kirim topilmadi", false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse("Bunday ta'minotchi mavjud emas", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Bunday valyuta turi mavjud emas", false);
        }

        boolean existsByFactureNumber = inputRepository.existsByFactureNumber(inputDTO.getFactureNumber());
        if (existsByFactureNumber) {
            return new ApiResponse("Bunday faktura raqam oldindan qo'shilgan", false);
        }

        Input input = optionalInput.get();

        boolean existsByFactureNumberAndIdNot = inputRepository.existsByFactureNumberAndIdNot(inputDTO.getFactureNumber(), input.getId());
        if (existsByFactureNumberAndIdNot) {
            return new ApiResponse("Bunday faktura raqami mavjud", false);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        input.setDate(timestamp);
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDTO.getFactureNumber());
        inputRepository.save(input);
        return new ApiResponse("Kirim saqlandi", true);
    }

    @Override
    public ApiResponse deleteInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new ApiResponse("Kirim topilmadi", false);
        }
        inputRepository.deleteById(id);
        return new ApiResponse("Kirim o'chirildi", true);
    }


}
