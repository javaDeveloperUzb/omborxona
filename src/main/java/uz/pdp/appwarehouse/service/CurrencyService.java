package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CurrencyDTO;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse addCurrency(CurrencyDTO currencyDTO) {
        boolean existsByName = currencyRepository.existsByName(currencyDTO.getName());
        if (existsByName) {
            return new ApiResponse("Bunday valyuta mavjud", false);
        }
        Currency currency = new Currency();
        currency.setName(currencyDTO.getName());
        currencyRepository.save(currency);
        return new ApiResponse("Valyuta muvaffaqiyatli qo'shildi", true);
    }

    public ApiResponse getCurrencyById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Bunday valyuta mavjud emas", false);
        }
        Currency currency = optionalCurrency.get();
        return new ApiResponse("OK", true, currency);
    }

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    public ApiResponse editCurrency(Integer id, CurrencyDTO currencyDTO) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Bunday valyuta mavjud emas", false);
        }

        boolean existsByNameNot = currencyRepository.existsByNameNot(currencyDTO.getName());
        if (existsByNameNot) {
            return new ApiResponse("Bunday valyuta mavjud", false);
        }
        Currency currency = optionalCurrency.get();
        currency.setName(currencyDTO.getName());
        currencyRepository.save(currency);
        return new ApiResponse("O'zgartirildi", true, currency);
    }

    public ApiResponse deleteCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Bunday valyuta mavjud emas", false);
        }
        currencyRepository.deleteById(id);
        return new ApiResponse("Valyuta o'chirildi", false);
    }
}
