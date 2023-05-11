package com.evercommerce.updox.prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {

    protected static final String FOUND_PRIMES_FILE = "foundNumber.ser";
    private List<Integer> generatedPrimes;
    // private static Set<Integer> foundPrimes;
    // private static Integer maxPrime;

    // static {
    //     foundPrimes = new HashSet<>();
    // }

    // public PrimeNumberGeneratorImpl() {
    //     maxPrime = Integer.MIN_VALUE;
    // }

    @Override
    public List<Integer> generate(int startingValue, int endingValue) {

        // swap if needed
        if(endingValue < startingValue) {
            int temp = endingValue;
            endingValue = startingValue;
            startingValue = temp;
        }

        // debug output
        StringBuilder sb = new StringBuilder();
        sb.append("\n* * * Generating prime numbers * * *\n").append("Range: ").append(startingValue).append(" - ").append(endingValue).append("\n");
        System.out.println(sb.toString());

        this.generatedPrimes = new ArrayList<>();
        for(int i = startingValue; i <= endingValue; i++) {
            if(isPrime(i)) {
                this.generatedPrimes.add(i);
            }
        }
        return this.generatedPrimes;
    }

    @Override
    public List<Integer> getGeneratedPrimeList() {
        return this.generatedPrimes;
    }

    @Override
    public boolean isPrime(int value) {

        return bruteForceMethod(value);

        // Integer stoppingPoint = value / 2; // stop at half of value
        // boolean flag = true;

        // // check if found prime numbers are divisors. this will save time needlessly iterating between primes.
        // for(Integer prime : foundPrimes) {
        //     if(prime > stoppingPoint) {
        //         flag = false;
        //         break;
        //     }
        //     if(value % prime == 0) { // found divisor, return false.
        //         return false;
        //     }
        // }

        // // if not found in stored primes, start iterating from max prime.
        // if(flag) {
        //     Integer divisor = foundPrimes.isEmpty() ? 2 : maxPrime + 1; // if no found primes, start with 2.
        //     while(divisor <= stoppingPoint) {
        //         if(value % divisor == 0) { // found divisor, return false.
        //             return false;
        //         }
        //         isPrime(divisor); // recurse on this number so it gets stored if prime and there are no gaps in our stored primes.
        //         divisor++;
        //     }
        // }
        
        // // Add to list of found prime numbers
        // foundPrimes.add(value);
        // return true;
    }

    private boolean bruteForceMethod(int value) {
        Integer stoppingPoint = value / 2; // stop at half of value
        int divisor = 2;

        while(divisor <= stoppingPoint) {
            if(value % divisor == 0) { // found divisor, return false.
                return false;
            }
            divisor++;
        }
        return value == 1 ? false : true;
    }

    @Override
    public void printNice(List<Integer> primes) {
        System.out.println("Primes:");
        for(Integer prime : primes) {
            System.out.println("  " + prime);
        }
    }
}
