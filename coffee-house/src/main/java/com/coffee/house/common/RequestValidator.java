package com.coffee.house.common;

import com.coffee.house.dto.CoffeeDto;
import com.coffee.house.dto.CoffeeVarietyDto;
import com.coffee.house.dto.OrderReqDto;
import com.coffee.house.dto.OrderUpdateDto;
import com.coffee.house.exception.BadRequestException;
import com.coffee.house.exception.ResourceNotFoundException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.coffee.house.common.AppConstants.*;

public class RequestValidator {

    public static void validateCoffeeDto(CoffeeVarietyDto coffeeVarietyDto) {
        if (StringUtils.isEmpty(coffeeVarietyDto.getCoffeeVariety()))
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "coffeeVariety"));

        if (Objects.isNull(coffeeVarietyDto.getPrice()))
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "price"));

        if (Objects.isNull(coffeeVarietyDto.getTotalServing()))
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "totalServing"));
    }

    public static void validateVariety(String variety) {
        if (!COFFEE_MAP.containsKey(variety.toUpperCase()))
            throw new ResourceNotFoundException("Coffee with variety " + variety.toUpperCase() + " does not exists");
    }

    public static void validateCoffeeDto(List<CoffeeDto> coffees) {

        if (coffees.isEmpty())
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "coffees"));

        for (CoffeeDto coffeeDto : coffees) {
            String variety = coffeeDto.getCoffeeVariety();
            Integer quantity = coffeeDto.getQuantity();

            if (StringUtils.isEmpty(variety))
                throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "coffeeVariety"));

            if (!COFFEE_MAP.containsKey(variety.toUpperCase()))
                throw new BadRequestException("Coffee variety " + variety + " does not exists");

            if (Objects.isNull(quantity))
                throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "quantity"));

        }
    }

    public static void validateOrderDto(OrderReqDto orderReqDto) {
        if (StringUtils.isEmpty(orderReqDto.getCustomerName()))
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "customerName"));

        if (StringUtils.isEmpty(orderReqDto.getPhoneNumber()))
            throw new BadRequestException(String.format(IS_REQUIRED_FIELD, "phoneNumber"));

        if (String.valueOf(orderReqDto.getPhoneNumber()).length() != 10)
            throw new BadRequestException(orderReqDto.getPhoneNumber() + " is not a valid phone number");

        validateCoffeeDto(orderReqDto.getCoffees());

    }

    public static void validateOrderId(String orderId) {
        if (!ORDER_MAP.containsKey(orderId))
            throw new ResourceNotFoundException("OrderId " + orderId + " not found");
    }

}
