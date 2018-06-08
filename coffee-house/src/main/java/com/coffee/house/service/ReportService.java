package com.coffee.house.service;

import com.coffee.house.dto.CoffeeDetailDto;
import com.coffee.house.dto.ReceiptDto;
import com.coffee.house.dto.ReportDto;
import com.coffee.house.model.Coffee;
import com.coffee.house.model.Customer;
import com.coffee.house.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.coffee.house.common.AppConstants.*;

@Service
public class ReportService {

    public ReceiptDto getReceipt(String orderId) {
        Order order = ORDER_MAP.get(orderId);
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setOrderId(orderId);
        receiptDto.setCustomerName(order.getCustomer().getName());
        receiptDto.setPhoneNumber(order.getCustomer().getPhoneNumber());

        float netTotal = 0f;
        List<CoffeeDetailDto> coffees = new ArrayList<>();
        for(Map.Entry<String, Integer> map: order.getCoffees().entrySet()) {
            String variety = map.getKey();
            Integer quantity = map.getValue();
            Coffee coffee = COFFEE_MAP.get(variety.toUpperCase());
            CoffeeDetailDto coffeeDetailDto = new CoffeeDetailDto();
            coffeeDetailDto.setCoffeeVariety(variety);
            coffeeDetailDto.setQuantity(quantity);
            float price = coffee.getPrice();
            coffeeDetailDto.setPrice(price);
            float total = price * quantity;
            coffeeDetailDto.setTotal(total);
            netTotal += total;
            coffees.add(coffeeDetailDto);
        }

        receiptDto.setCoffees(coffees);
        receiptDto.setNetTotal(netTotal);
        return receiptDto;
    }

    public Set<Customer> getCustomers() {
        return CUSTOMER_SET;
    }

    public Set<ReportDto> getReport() {
        Set<ReportDto> reports = new HashSet<>();
        COFFEE_MAP.forEach((variety, coffee) -> {
            ReportDto reportDto = new ReportDto();
            reportDto.setCoffeeVariety(coffee.getCoffeeVariety());
            reportDto.setTotalServing(coffee.getTotalServing());
            reportDto.setTotalSold(coffee.getTotalSold());
            reports.add(reportDto);
        });
        return reports;
    }

}
