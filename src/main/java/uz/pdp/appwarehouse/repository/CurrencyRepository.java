package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);
    boolean existsByNameNot(String name);
}
