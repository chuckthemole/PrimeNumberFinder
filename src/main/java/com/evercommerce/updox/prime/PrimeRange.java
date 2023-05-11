package com.evercommerce.updox.prime;

public class PrimeRange {

    private Integer lower;
    private Integer upper;

    public void setLower(Integer lower) {
        this.lower = lower;
    }
    public Integer getLower() {
        if(Integer.compare(lower, upper) > 0) {
            swap();
        }
        return this.lower;
    }
    public void setUpper(Integer upper) {
        this.upper = upper;
    }
    public Integer getUpper() {
        if(Integer.compare(lower, upper) > 0) {
            swap();
        }
        return this.upper;
    }

    private void swap() {
        Integer temp = this.lower;
        this.lower = this.upper;
        this.upper = temp;
    }
}
