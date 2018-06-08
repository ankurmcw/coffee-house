package com.coffee.house.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Customer implements Comparable<Customer> {

    private String name;

    private Long phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name.toUpperCase(), customer.name.toUpperCase()) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name.toUpperCase(), phoneNumber);
    }

    @Override
    public int compareTo(Customer o) {
        int compareValue = this.name.toUpperCase().compareTo(o.name.toUpperCase());
        if (compareValue == 0)
            compareValue = this.phoneNumber.compareTo(o.phoneNumber);

        return compareValue;
    }
}
