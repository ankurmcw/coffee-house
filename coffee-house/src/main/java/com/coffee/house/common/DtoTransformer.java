package com.coffee.house.common;

import com.coffee.house.dto.CoffeeDto;
import com.coffee.house.dto.CoffeeVarietyDto;
import com.coffee.house.dto.OrderReqDto;
import com.coffee.house.dto.OrderResDto;
import com.coffee.house.model.Coffee;
import com.coffee.house.model.Customer;
import com.coffee.house.model.Order;

import java.util.*;

import static com.coffee.house.common.AppConstants.CUSTOMER_SET;

public class DtoTransformer {

    public static Coffee transform(CoffeeVarietyDto coffeeVarietyDto) {
        Coffee coffee = new Coffee();
        coffee.setCoffeeVariety(coffeeVarietyDto.getCoffeeVariety());
        coffee.setPrice(coffeeVarietyDto.getPrice());
        coffee.setTotalServing(coffeeVarietyDto.getTotalServing());
        coffee.setTotalSold(0);
        return coffee;
    }

    public static CoffeeVarietyDto transform(Coffee coffee) {
        CoffeeVarietyDto coffeeVarietyDto = new CoffeeVarietyDto();
        coffeeVarietyDto.setCoffeeVariety(coffee.getCoffeeVariety());
        coffeeVarietyDto.setPrice(coffee.getPrice());
        coffeeVarietyDto.setTotalServing(coffee.getTotalServing());
        return coffeeVarietyDto;
    }

    public static Set<CoffeeVarietyDto> transform(Collection<Coffee> coffees) {
        Set<CoffeeVarietyDto> coffeeVarietyDtos = new HashSet<>();
        for (Coffee coffee: coffees) {
            CoffeeVarietyDto coffeeVarietyDto = transform(coffee);
            coffeeVarietyDtos.add(coffeeVarietyDto);
        }
        return coffeeVarietyDtos;
    }

    public static Order transform(OrderReqDto orderReqDto) {
        Customer customer = new Customer();
        customer.setName(orderReqDto.getCustomerName());
        customer.setPhoneNumber(orderReqDto.getPhoneNumber());
        CUSTOMER_SET.add(customer);
        Order order = new Order();
        order.setCustomer(customer);
        Map<String, Integer> coffees = new HashMap<>();
        for (CoffeeDto coffeeDto : orderReqDto.getCoffees()) {
            coffees.put(coffeeDto.getCoffeeVariety().toUpperCase(), coffeeDto.getQuantity());
        }
        order.setCoffees(coffees);
        order.setTimeStamp(new Date());
        return order;
    }

    public static OrderResDto transform(String orderId, Order order) {
        OrderResDto orderResDto = new OrderResDto();
        orderResDto.setOrderId(orderId);
        orderResDto.setCustomerName(order.getCustomer().getName());
        orderResDto.setPhoneNumber(order.getCustomer().getPhoneNumber());
        List<CoffeeDto> coffees = new ArrayList<>();
        order.getCoffees().forEach((variety, quantity) -> {
            CoffeeDto coffeeDto = new CoffeeDto();
            coffeeDto.setCoffeeVariety(variety);
            coffeeDto.setQuantity(quantity);
            coffees.add(coffeeDto);
        });
        orderResDto.setCoffees(coffees);
        orderResDto.setTimeStamp(order.getTimeStamp());
        return orderResDto;
    }

    public static List<OrderResDto> transform(Map<String, Order> orderMap) {
        List<OrderResDto> orders = new ArrayList<>();
        orderMap.forEach((orderId, order) -> {
            OrderResDto orderResDto = transform(orderId, order);
            orders.add(orderResDto);
        });
        return orders;
    }

}
