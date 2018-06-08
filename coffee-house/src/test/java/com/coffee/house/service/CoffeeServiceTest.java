package com.coffee.house.service;

import com.coffee.house.dto.CoffeeVarietyDto;
import com.coffee.house.model.Coffee;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static com.coffee.house.common.AppConstants.COFFEE_MAP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class CoffeeServiceTest {

    @InjectMocks
    private CoffeeService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyCreateVariety() {
        CoffeeVarietyDto dto = new CoffeeVarietyDto();
        dto.setCoffeeVariety("Mocha");
        dto.setPrice(135.0F);
        dto.setTotalServing(150);

        service.createVariety(dto);
        assertEquals(4, COFFEE_MAP.size());
    }

    @Test
    public void verifyGetVarieties() {
        Set<CoffeeVarietyDto> dtos = service.getVarieties();
        assertEquals(4, dtos.size());
    }

    @Test
    public void verifyGetVariety() {
        CoffeeVarietyDto dto = service.getVariety("Espresso");
        assertNotNull(dto);
    }

    @Test
    public void verifyDeleteVariety() {
        service.deleteVariety("Espresso");
        assertEquals(3, COFFEE_MAP.size());
    }
}
