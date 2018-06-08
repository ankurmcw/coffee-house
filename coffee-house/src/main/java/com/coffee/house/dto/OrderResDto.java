package com.coffee.house.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResDto {

    private String orderId;

    private String customerName;

    private Long phoneNumber;

    private List<CoffeeDto> coffees;

    private Date timeStamp;
}
