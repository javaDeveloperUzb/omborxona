package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Input;

import java.util.Date;
import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String factureNumber);

    boolean existsByFactureNumberAndIdNot(String factureNumber, Integer id);

    @Query(value = "select * from input where date between :startDate and :endDate",nativeQuery = true)
    List<Input> getAllInputIdBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
