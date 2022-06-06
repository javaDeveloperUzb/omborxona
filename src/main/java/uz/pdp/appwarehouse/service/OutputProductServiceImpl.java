package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.OutputProductRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductServiceImpl implements OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    InputProductService inputProductService;

    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse addOutputProduct(OutputProductDTO outputProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputProductDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Currency not found", false);
        }

        InputProduct inputProduct = inputProductRepository.findByProductId(outputProductDTO.getProductId()).get();
//        System.out.println(inputProduct.getAmount());
        Double amount = inputProduct.getAmount() - outputProductDTO.getAmount();
        if (amount < 0) {
            return new ApiResponse("Omborxonada mahsulot yetarli emas", false);
        }
        inputProduct.setAmount(amount);
        inputProductRepository.save(inputProduct);

        OutputProduct outputProduct = new OutputProduct();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        outputProduct.setDate(timestamp);
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setCurrency(optionalCurrency.get());
        outputProductRepository.save(outputProduct);
        return new ApiResponse("OutputProduct saved", true);
    }

    @Override
    public ApiResponse getOutputProductById(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.map(outputProduct -> new ApiResponse("OK", true, outputProduct)).orElseGet(() -> new ApiResponse("OutputProduct not found", false, new OutputProduct()));
    }

    @Override
    public List<OutputProduct> getAllOutputProduct() {
        return outputProductRepository.findAll();
    }

    @Override
    public ApiResponse editOutputProduct(Integer id, OutputProductDTO outputProductDTO) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("OutputProduct not found", false);
        }
        System.out.println(optionalOutputProduct.get().getAmount());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputProductDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse("Currency not found", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product not found", false);
        }

        InputProduct inputProduct = inputProductRepository.findByProductId(outputProductDTO.getProductId()).get();
        Double amount = inputProduct.getAmount() - outputProductDTO.getAmount() + optionalOutputProduct.get().getAmount();
        if (amount < 0) {
            return new ApiResponse("Omborxonada mahsulot yetarli emas", false);
        }
        inputProduct.setAmount(amount);
        inputProductRepository.save(inputProduct);

        OutputProduct outputProduct = optionalOutputProduct.get();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        outputProduct.setDate(timestamp);
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setCurrency(optionalCurrency.get());

        outputProductRepository.save(outputProduct);
        return new ApiResponse("OutputProduct edited", true);
    }

    @Override
    public ApiResponse deleteOutputProduct(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("OutputProduct not found", false);
        }
        outputProductRepository.deleteById(id);
        return new ApiResponse("OutputProduct deleted", true);
    }
}
