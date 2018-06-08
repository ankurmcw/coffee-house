package com.coffee.house.service;

import com.coffee.house.dto.CoffeeDto;
import com.coffee.house.dto.OrderReqDto;
import com.coffee.house.dto.OrderResDto;
import com.coffee.house.dto.OrderUpdateDto;
import com.coffee.house.model.Coffee;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static com.coffee.house.common.AppConstants.ORDER_MAP;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class OrderServiceTest {

    @InjectMocks
    private OrderService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Coffee coffeeLatte = new Coffee();
        coffeeLatte.setCoffeeVariety("Latte");
        coffeeLatte.setPrice(120.0F);
        coffeeLatte.setTotalServing(150);
        coffeeLatte.setTotalSold(3);

        COFFEE_MAP.put("LATTE", coffeeLatte);
    }

    @Test
    public void verifyCreateOrder() {
        List<CoffeeDto> coffees = new ArrayList<>();
        CoffeeDto coffeeDto = new CoffeeDto();
        coffeeDto.setQuantity(2);
        coffeeDto.setCoffeeVariety("Latte");
        coffees.add(coffeeDto);

        OrderReqDto reqDto = new OrderReqDto();
        reqDto.setCustomerName("Ankur");
        reqDto.setPhoneNumber(9886423852L);
        reqDto.setCoffees(coffees);

        OrderResDto resDto = service.createOrder(reqDto);
        assertNotNull(resDto);
    }

    @Test
    public void verifyGetOrders() {
        List<OrderResDto> resDtos = service.getOrders();
        assertEquals(2, resDtos.size());
    }

    @Test
    public void verifyGetOrder() {
        Set<String> orderIds = ORDER_MAP.keySet();
        for (String orderId: orderIds) {
            OrderResDto resDto = service.getOrder(orderId);
            assertNotNull(resDto);
        }
    }

    @Test
    public void verifyUpdateOrder() {
        Set<String> orderIds = ORDER_MAP.keySet();
        for (String orderId: orderIds) {
            OrderUpdateDto updateDto = new OrderUpdateDto();

            List<CoffeeDto> coffees = new ArrayList<>();
            CoffeeDto coffeeDto = new CoffeeDto();
            coffeeDto.setQuantity(1);
            coffeeDto.setCoffeeVariety("Latte");
            updateDto.setCoffees(coffees);

            OrderResDto resDto = service.updateOrder(orderId, updateDto);
            assertNotNull(resDto);
        }
    }

    @Test
    public void verifyDeleteOrder() {
        Set<String> orderIds = ORDER_MAP.keySet();
        for (String orderId: orderIds) {
            int size = ORDER_MAP.size();
            service.deleteOrder(orderId);
            assertEquals(size - 1, ORDER_MAP.size());
        }
    }

}
