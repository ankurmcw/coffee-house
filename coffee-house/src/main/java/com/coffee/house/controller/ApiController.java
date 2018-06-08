package com.coffee.house.controller;

import com.coffee.house.dto.*;
import com.coffee.house.model.Customer;
import com.coffee.house.service.CoffeeService;
import com.coffee.house.service.OrderService;
import com.coffee.house.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.coffee.house.common.RequestValidator.*;

@RestController
@RequestMapping("/v1")
public class ApiController {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReportService reportService;

    @PostMapping(value = "/coffees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCoffeeVariety(@RequestBody CoffeeVarietyDto coffeeVarietyDto) {
        validateCoffeeDto(coffeeVarietyDto);
        coffeeService.createVariety(coffeeVarietyDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/coffees/{variety}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCoffeeVariety(@PathVariable("variety") String variety, @RequestBody CoffeeVarietyDto coffeeVarietyDto) {
        validateVariety(variety);
        coffeeVarietyDto.setCoffeeVariety(variety);
        validateCoffeeDto(coffeeVarietyDto);
        coffeeService.updateVariety(coffeeVarietyDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/coffees")
    public ResponseEntity<Set<CoffeeVarietyDto>> getCoffeeVarieties() {
        return new ResponseEntity<>(coffeeService.getVarieties(), HttpStatus.OK);
    }

    @GetMapping("/coffees/{variety}")
    public ResponseEntity<CoffeeVarietyDto> getCoffeeVariety(@PathVariable("variety") String variety) {
        validateVariety(variety);
        return new ResponseEntity<>(coffeeService.getVariety(variety), HttpStatus.OK);
    }

    @DeleteMapping("/coffees/{variety}")
    public ResponseEntity deleteVariety(@PathVariable("variety") String variety) {
        validateVariety(variety);
        coffeeService.deleteVariety(variety);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResDto> createOrder(@RequestBody OrderReqDto orderReqDto) {
        validateOrderDto(orderReqDto);
        return new ResponseEntity<>(orderService.createOrder(orderReqDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResDto> updateOrder(@PathVariable("orderId") String orderId, @RequestBody OrderUpdateDto updateDto) {
        validateOrderId(orderId);
        validateCoffeeDto(updateDto.getCoffees());
        return new ResponseEntity(orderService.updateOrder(orderId, updateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResDto>> getOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResDto> getOrder(@PathVariable("orderId") String orderId) {
        validateOrderId(orderId);
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") String orderId) {
        validateOrderId(orderId);
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/receipts/{orderId}")
    public ResponseEntity<ReceiptDto> getReceipt(@PathVariable("orderId") String orderId) {
        validateOrderId(orderId);
        return new ResponseEntity<>(reportService.getReceipt(orderId), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<Set<Customer>> getCustomers() {
        return new ResponseEntity<>(reportService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<Set<ReportDto>> getReports() {
        return new ResponseEntity<>(reportService.getReport(), HttpStatus.OK);
    }

}
