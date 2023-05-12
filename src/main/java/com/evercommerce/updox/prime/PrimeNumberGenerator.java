package com.evercommerce.updox.prime;

import java.util.List;

public interface PrimeNumberGenerator {
    /**
     * 
     * @param startingValue starting value, inclusive.
     * @param endingValue ending value, inclusive.
     * @return list of prime numbers from startingValue to endingValue, inclusive.
     */
    public List<Integer> generate(int startingValue, int endingValue);

    /**
     * 
     * @param value value to check
     * @return if value is prime
     */
    public boolean isPrime(int value);

    /**
     * 
     * @param primes prime list to print
     */
    public void printNice(List<Integer> primes);

    /**
     * 
     * @param useBruteForce flag to set, true to useBruteForce, false to use optimized solution.
     */
    public void useBruteForce(boolean useBruteForce);

    /**
     * 
     * @return time it takes to generate primes
     */
    public Long getDuration();
}