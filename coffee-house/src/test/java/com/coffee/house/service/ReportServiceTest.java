package com.coffee.house.service;

import com.coffee.house.dto.OrderResDto;
import com.coffee.house.dto.ReceiptDto;
import com.coffee.house.dto.ReportDto;
import com.coffee.house.model.Coffee;
import com.coffee.house.model.Customer;
import com.coffee.house.model.Order;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static com.coffee.house.common.AppConstants.CUSTOMER_SET;
import static com.coffee.house.common.AppConstants.ORDER_MAP;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class ReportServiceTest {

    @InjectMocks
    private ReportService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Coffee coffeeLatte = new Coffee();
        coffeeLatte.setCoffeeVariety("Latte");
        coffeeLatte.setPrice(120.0F);
        coffeeLatte.setTotalServing(150);
        coffeeLatte.setTotalSold(3);

        COFFEE_MAP.put("LATTE", coffeeLatte);

        Customer customer = new Customer();
        customer.setName("Ankur");
        customer.setPhoneNumber(9886423852L);

        CUSTOMER_SET.add(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setTimeStamp(new Date());
        Map<String, Integer> coffees = new HashMap<>();
        coffees.put("Latte", 3);
        order.setCoffees(coffees);

        String orderId = UUID.randomUUID().toString();
        ORDER_MAP.put(orderId, order);
    }

    @Test
    public void verifyGetReceipt() {
        Set<String> orderIds = ORDER_MAP.keySet();
        for (String orderId: orderIds) {
            ReceiptDto dto = service.getReceipt(orderId);
            assertNotNull(dto);
        }
    }

    @Test
    public void verifyGetCustomers() {
        Set<Customer> customers = service.getCustomers();
        assertEquals(1, customers.size());
    }

    @Test
    public void verifyGetReport() {
        Set<ReportDto> reports = service.getReport();
        assertEquals(3, reports.size());
    }
}
