package uz.pdp.appwarehouse.repository;

import org.apache.catalina.webresources.WarResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    List<Warehouse> findAllById(Integer id);

    Optional<Warehouse> findByProductId(Integer product_id);

}
