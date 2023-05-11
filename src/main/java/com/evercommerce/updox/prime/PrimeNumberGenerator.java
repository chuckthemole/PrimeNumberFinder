package com.evercommerce.updox.prime;

import java.util.List;

public interface PrimeNumberGenerator {
    List<Integer> generate(int startingValue, int endingValue);
    boolean isPrime(int value);
    public void printNice(List<Integer> primes);
}