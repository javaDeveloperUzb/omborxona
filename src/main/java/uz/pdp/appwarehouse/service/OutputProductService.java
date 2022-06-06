package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.OutputProductDTO;

import java.util.List;

public interface OutputProductService {
    ApiResponse addOutputProduct(OutputProductDTO outputProductDTO);

    ApiResponse getOutputProductById(Integer id);

    List<OutputProduct> getAllOutputProduct();

    ApiResponse editOutputProduct(Integer id, OutputProductDTO outputProductDTO);

    ApiResponse deleteOutputProduct(Integer id);
}
