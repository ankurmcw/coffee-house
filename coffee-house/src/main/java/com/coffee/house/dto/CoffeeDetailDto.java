package com.coffee.house.dto;

import lombok.Data;

@Data
public class CoffeeDetailDto {

    private String coffeeVariety;

    private Integer quantity;

    private Float price;

    private Float total;
}
