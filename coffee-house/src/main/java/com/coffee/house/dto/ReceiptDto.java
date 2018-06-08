package com.coffee.house.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReceiptDto {

    private String orderId;

    private String customerName;

    private Long phoneNumber;

    private List<CoffeeDetailDto> coffees;

    private Float netTotal;
}
