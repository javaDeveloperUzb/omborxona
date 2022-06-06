package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ProductDTO;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse addProduct(ProductDTO productDTO) {
        boolean exists = productRepository.existsByName(productDTO.getName());
        if (exists) {
            return new ApiResponse("Bunday maxsulot mavjud", false);
        }
        Product product = new Product();
        product.setName(productDTO.getName());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ApiResponse("Bunday o'lchov birlik mavjud emas", false);
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new ApiResponse("Maxsulot qo'shildi", true);
    }

    public ApiResponse getProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ApiResponse("Maxsulot:", true, product)).orElseGet(() -> new ApiResponse("Bunday maxsulot mavjud emas", false));
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public ApiResponse editProduct(Integer id, ProductDTO productDTO) {
        boolean existsByName = productRepository.existsByNameNot(productDTO.getName());
        if (existsByName) {
            return new ApiResponse("Bunday maxsulot mavjud", false);
        }
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ApiResponse("Bunday o'lchov birlik mavjud emas", false);
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new ApiResponse("Maxsulot o'zgartirildi", true);
    }

    public ApiResponse deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Bunday maxsulot mavjud emas", false);
        }
        productRepository.deleteById(id);
        return new ApiResponse("Maxsulot o'chirildi:", true);
    }

}
