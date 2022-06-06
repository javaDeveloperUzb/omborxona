package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
    List<InputProduct> findAllByInputId(Integer id);

    Optional<InputProduct> findByProductId(Integer product_id);

    @Query(value = "select * from input_product where expire_date <=:cer_date",nativeQuery = true)
    List<InputProduct> findAllExpiring(Date cer_date);

    @Query(value = "select * from input_product where expire_date between :startDate and :endDate", nativeQuery = true)
    List<InputProduct> findAllByWillBeExpired(@Param("startDate") Date start, @Param("endDate") Date end);

    boolean existsByProductId(Integer product_id);
}
