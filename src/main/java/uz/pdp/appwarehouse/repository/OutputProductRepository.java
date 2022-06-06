package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.OutputProduct;

import java.util.Date;
import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
        List<OutputProduct> findAllByIdOrderByAmountDesc(Integer id);
        @Query(value = "select * from output_product where date between :startDate and :endDate",nativeQuery = true)
        List<OutputProduct> getOutputIdBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
