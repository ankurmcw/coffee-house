package com.coffee.house.common;

import com.coffee.house.model.Coffee;
import com.coffee.house.model.Customer;
import com.coffee.house.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class AppConstants {

    public static final String IS_REQUIRED_FIELD = "%s is a required field";

    public static Map<String, Coffee> COFFEE_MAP = new ConcurrentHashMap<>();

    public static Map<String, Order> ORDER_MAP = new ConcurrentHashMap<>();

    public static Set<Customer> CUSTOMER_SET = new TreeSet<>();
}
