package com.evercommerce.updox.prime;

public class Prime {

    private Integer number;

    public Prime(Integer number) {
        this.number = number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
