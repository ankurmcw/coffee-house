package com.coffee.house.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Order {

    private Customer customer;

    private Map<String, Integer> coffees;

    private Date timeStamp;

}
