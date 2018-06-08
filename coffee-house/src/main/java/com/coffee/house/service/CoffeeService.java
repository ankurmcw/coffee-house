package com.coffee.house.service;

import com.coffee.house.dto.CoffeeVarietyDto;
import com.coffee.house.exception.BadRequestException;
import com.coffee.house.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static com.coffee.house.common.DtoTransformer.transform;

@Service
public class CoffeeService {

    public void createVariety(CoffeeVarietyDto coffeeVarietyDto) {
        String coffeeVariety = coffeeVarietyDto.getCoffeeVariety().toUpperCase();
        if (COFFEE_MAP.containsKey(coffeeVariety))
            throw new BadRequestException("Coffee with variety " + coffeeVariety + " already exists");

        Coffee coffee = transform(coffeeVarietyDto);
        COFFEE_MAP.put(coffeeVariety, coffee);
    }

    public void updateVariety(CoffeeVarietyDto coffeeVarietyDto) {
        String coffeeVariety = coffeeVarietyDto.getCoffeeVariety().toUpperCase();
        Coffee coffee = transform(coffeeVarietyDto);
        COFFEE_MAP.replace(coffeeVariety, coffee);
    }

    public Set<CoffeeVarietyDto> getVarieties() {
        return transform(COFFEE_MAP.values());
    }

    public CoffeeVarietyDto getVariety(String variety) {
        return transform(COFFEE_MAP.get(variety.toUpperCase()));
    }

    public void deleteVariety(String variety) {
        COFFEE_MAP.remove(variety.toUpperCase());
    }
}
