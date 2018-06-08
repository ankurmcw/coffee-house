package com.coffee.house.model;

import lombok.Data;

@Data
public class Coffee {

    private String coffeeVariety;

    private Float price;

    private Integer totalServing;

    private Integer totalSold;

}
