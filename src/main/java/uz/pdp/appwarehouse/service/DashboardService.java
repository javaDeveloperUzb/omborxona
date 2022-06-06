package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;

import java.util.List;

public interface DashboardService {
    List<InputProduct> getDailyInputProduct();

    List<OutputProduct> getDailyTheMostOutputProducts();

    List<InputProduct> getAllExpiringInputProduct();

    List<InputProduct> warnToWillBeExpiredProduct(Integer cerDay);
}
