package com.evercommerce.updox.prime;

import java.util.List;

public class PrimeList {

    private List<Integer> primes;

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }
    public List<Integer> getPrimes() {
        return this.primes;
    }
    public void printNice() {
        System.out.println("List of primes:");
        for(Integer prime : primes) {
            System.out.println("  " + prime);
        }
    }
}
