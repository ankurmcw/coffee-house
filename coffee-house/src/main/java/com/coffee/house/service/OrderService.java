package com.coffee.house.service;

import com.coffee.house.dto.CoffeeDto;
import com.coffee.house.dto.OrderReqDto;
import com.coffee.house.dto.OrderResDto;
import com.coffee.house.dto.OrderUpdateDto;
import com.coffee.house.exception.BadRequestException;
import com.coffee.house.model.Coffee;
import com.coffee.house.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static com.coffee.house.common.AppConstants.ORDER_MAP;
import static com.coffee.house.common.DtoTransformer.transform;

@Service
public class OrderService {

    public OrderResDto createOrder(OrderReqDto orderReqDto) {
        updateCoffeeSold(orderReqDto.getCoffees());
        Order order = transform(orderReqDto);
        String orderId = UUID.randomUUID().toString();
        ORDER_MAP.put(orderId, order);
        return transform(orderId, order);
    }

    public OrderResDto updateOrder(String orderId, OrderUpdateDto updateDto) {
        Order order = ORDER_MAP.get(orderId);
        order.getCoffees().forEach((variety, quantity) -> {
            Coffee coffee = COFFEE_MAP.get(variety.toUpperCase());
            int totalSold = coffee.getTotalSold();
            coffee.setTotalSold(totalSold - quantity);
        });
        updateCoffeeSold(updateDto.getCoffees());
        Map<String, Integer> coffees = new HashMap<>();
        updateDto.getCoffees().forEach(coffeeDto -> {
            coffees.put(coffeeDto.getCoffeeVariety().toUpperCase(), coffeeDto.getQuantity());
        });

        order.setCoffees(coffees);
        order.setTimeStamp(new Date());
        ORDER_MAP.replace(orderId, order);
        return transform(orderId, order);
    }

    public List<OrderResDto> getOrders() {
        return transform(ORDER_MAP);
    }

    public OrderResDto getOrder(String orderId) {
        Order order = ORDER_MAP.get(orderId);
        return transform(orderId, order);
    }

    public void deleteOrder(String orderId) {
        ORDER_MAP.remove(orderId);
    }

    private void updateCoffeeSold(List<CoffeeDto> coffees) {
        coffees.forEach(coffeeDto -> {
            String variety = coffeeDto.getCoffeeVariety();
            int quantity = coffeeDto.getQuantity();
            Coffee coffee = COFFEE_MAP.get(variety.toUpperCase());
            int remainingServings = coffee.getTotalServing() - coffee.getTotalSold();
            if (remainingServings < quantity)
                throw new BadRequestException("Not enough servings for variety " + variety);
            else
                coffee.setTotalSold(coffee.getTotalSold() + quantity);

        });
    }

}
