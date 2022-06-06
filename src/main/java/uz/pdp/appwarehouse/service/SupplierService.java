package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDTO;

import java.util.List;

public interface SupplierService {
    ApiResponse addSupplier(SupplierDTO supplierDTO);

    ApiResponse getSupplierById(Integer id);

    List<Supplier> getAllSupplier();

    ApiResponse editSupplier(Integer id, SupplierDTO supplierDTO);

    ApiResponse deleteSupplier(Integer id);
}
