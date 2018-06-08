package com.coffee.house.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyGetCoffeeVarieties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/coffees"))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", "application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void verifyGetCoffeeVariety() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/coffees/espresso"))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", "application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.coffeeVariety").exists())
                .andExpect(jsonPath("$.coffeeVariety").value("Espresso"))
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.price").value("135.0"))
                .andExpect(jsonPath("$.totalServing").exists())
                .andExpect(jsonPath("$.totalServing").value("150"))
                .andDo(print());
    }

    @Test
    public void verifyCreateCoffeeVariety() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"coffeeVariety\": \"Cappuccino\",\n" +
                        "  \"price\": 120,\n" +
                        "  \"totalServing\": 100\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void verifyCreateCoffeeVarietyError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"price\": 120,\n" +
                        "  \"totalServing\": 100\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
