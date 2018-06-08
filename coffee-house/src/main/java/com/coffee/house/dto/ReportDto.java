package com.coffee.house.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ReportDto {

    private String coffeeVariety;

    private Integer totalServing;

    private Integer totalSold;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReportDto reportDto = (ReportDto) o;
        return Objects.equals(coffeeVariety.toUpperCase(), reportDto.coffeeVariety.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coffeeVariety.toUpperCase());
    }
}
