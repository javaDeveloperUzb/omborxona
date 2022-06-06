package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse addInputProduct(InputProductDTO inputProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Bunday maxsulot mavjud emas", false);
        }

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent()) {
            return new ApiResponse("Bunday kirim mavjud emas", false);
        }

            Warehouse warehouse = new Warehouse();
            warehouse.setProduct(optionalProduct.get());
            warehouse.setAmount(inputProductDTO.getAmount());
            warehouse.setPrice(inputProductDTO.getPrice());
            warehouse.setExpireDate(inputProductDTO.getExpireDate());
            warehouse.setInput(optionalInput.get());
            warehouseRepository.save(warehouse);

        boolean existsByProductIdNot = inputProductRepository.existsByProductId(inputProductDTO.getProductId());
        if (existsByProductIdNot) {
            InputProduct inputProduct = inputProductRepository.findByProductId(inputProductDTO.getProductId()).get();
            inputProduct.setAmount(inputProduct.getAmount() + inputProductDTO.getAmount());
            inputProduct.setPrice(inputProductDTO.getPrice());
            inputProductRepository.save(inputProduct);
            return new ApiResponse("Kiruvchi mahsulot saqlandi", true);
        }

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new ApiResponse("Kiruvchi mahsulot saqlandi", true);
    }

    public ApiResponse getInputProductById(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.map(inputProduct -> new ApiResponse("OK", true, inputProduct)).orElseGet(() -> new ApiResponse("Bunday kiruvchi maxsulot topilmadi", false));
    }

    public List<InputProduct> getAllInputProduct() {
        return inputProductRepository.findAll();
    }

    public ApiResponse editInputProduct(Integer id, InputProductDTO inputProductDTO) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new ApiResponse("Bunday kiruvchi maxsulot topilmadi", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Bunday mahsulot mavjud emas", false);
        }

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent()) {
            return new ApiResponse("Bunday kirim mavjud emas", false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("Kiruvchi mahsulot o'zgartirildi", true);
    }

    public ApiResponse deleteInputProduct(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new ApiResponse("Bunday kiruvchi maxsulot topilmadi", false);
        }
        inputProductRepository.deleteById(id);
        return new ApiResponse("Kiruvchi maxsulot o'chirildi", true);
    }
}
