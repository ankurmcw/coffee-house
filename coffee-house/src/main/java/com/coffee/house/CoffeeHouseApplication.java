package com.coffee.house;

import com.coffee.house.model.Coffee;
import com.coffee.house.model.Customer;
import com.coffee.house.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static com.coffee.house.common.AppConstants.CUSTOMER_SET;
import static com.coffee.house.common.AppConstants.ORDER_MAP;

@SpringBootApplication
public class CoffeeHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeHouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUp() {
		return (args -> {
			Coffee coffeeLatte = new Coffee();
			coffeeLatte.setCoffeeVariety("Latte");
			coffeeLatte.setPrice(120.0F);
			coffeeLatte.setTotalServing(150);
			coffeeLatte.setTotalSold(3);

			Coffee coffeeEspresso = new Coffee();
			coffeeEspresso.setCoffeeVariety("Espresso");
			coffeeEspresso.setPrice(135.0F);
			coffeeEspresso.setTotalServing(150);
			coffeeEspresso.setTotalSold(2);

			COFFEE_MAP.put("LATTE", coffeeLatte);
			COFFEE_MAP.put("ESPRESSO", coffeeEspresso);

			Customer customer = new Customer();
			customer.setName("Ankur");
			customer.setPhoneNumber(9886423852L);

			CUSTOMER_SET.add(customer);

			Order order = new Order();
			order.setCustomer(customer);
			order.setTimeStamp(new Date());
			Map<String, Integer> coffees = new HashMap<>();
			coffees.put("Latte", 3);
			coffees.put("Espresso", 2);
			order.setCoffees(coffees);

			String orderId = UUID.randomUUID().toString();
			ORDER_MAP.put(orderId, order);

			System.out.println("Setup data has been inserted successfully");
		});
	}
}
