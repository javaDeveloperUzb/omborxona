package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public ApiResponse addSupplier(SupplierDTO supplierDTO) {
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplierDTO.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("Supplier already exists", false);
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse("Supplier added", true);
    }

    @Override
    public ApiResponse getSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.map(supplier -> new ApiResponse("Ok", true, supplier)).orElseGet(() -> new ApiResponse("Supplier not found", false));
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public ApiResponse editSupplier(Integer id, SupplierDTO supplierDTO) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse("Supplier not found", false);
        }

        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumberAndIdNot(supplierDTO.getPhoneNumber(), id);
        if (existsByPhoneNumber) {
            return new ApiResponse("Phone number already exists", false);
        }


        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDTO.getName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse("Supplier edited", true);
    }

    @Override
    public ApiResponse deleteSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse("Supplier not found", false);
        }
        supplierRepository.deleteById(id);
        return new ApiResponse("Supplier deleted", true);
    }
}
