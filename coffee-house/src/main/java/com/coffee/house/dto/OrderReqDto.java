package com.coffee.house.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderReqDto {

    private String customerName;

    private Long phoneNumber;

    private List<CoffeeDto> coffees;

}
