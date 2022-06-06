package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.service.DashboardService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    // Kunlik kirim bo’lgan mahsulotlar (qiymati, umumiy summasi)

    @GetMapping("/daily-input-product")
    public List<InputProduct> getDailyInputProduct() {
        return dashboardService.getDailyInputProduct();
    }

    // Kunlik eng ko’p chiqim qilingan mahsulotlar
    @GetMapping("/daily-most-out-product")
    public List<OutputProduct> getDailyOutputProduct() {
        return dashboardService.getDailyTheMostOutputProducts();
    }

    // Yaroqlilik muddati yetib qolgan mahsulotlarni olish.
    @GetMapping("/expiring-input-product")
    public List<InputProduct> getAllExpiringInputProduct() {
        return dashboardService.getAllExpiringInputProduct();
    }

    // Mahsulotlarning yaroqlilik muddatining tugashiga ma’lum bir vaqt qolganida
    // tizim ogohlantirishi kerak. Bunda ogohlantiriladigan vaqtni admin kiritib qo’yadi.
    @GetMapping("/will-expired-input-product")
    public List<InputProduct> getWillBeExpiredInputProduct(@RequestParam Integer cerDay) {
        return dashboardService.warnToWillBeExpiredProduct(cerDay);
    }
}
