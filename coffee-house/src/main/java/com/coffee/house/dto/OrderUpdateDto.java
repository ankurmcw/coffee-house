package com.coffee.house.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDto {

    private List<CoffeeDto> coffees;
}
