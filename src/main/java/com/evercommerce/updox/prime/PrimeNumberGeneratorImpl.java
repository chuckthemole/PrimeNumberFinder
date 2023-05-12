package com.evercommerce.updox.prime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {

    private static PrimeTracker primeTracker; // memoization method for keeping track of primes.
    private boolean useBruteForce;
    private long duration;

    static {
        primeTracker = new PrimeTrackerImpl();
    }

    public PrimeNumberGeneratorImpl() {
        this.useBruteForce = false;
        generate(2, primeTracker.size() - 1); // generate primes up to NODE_SIZE (100) upon construction
    }

    @Override
    public List<Integer> generate(int startingValue, int endingValue) {
        final long startTime = System.nanoTime();
        // swap if needed
        if(endingValue < startingValue) {
            int temp = endingValue;
            endingValue = startingValue;
            startingValue = temp;
        }

        findPrimesUpToValue(endingValue);

        // debug output
        StringBuilder sb = new StringBuilder();
        sb.append("\n* * * Generating prime numbers * * *\n").append("Range: ").append(startingValue).append(" - ").append(endingValue).append("\n");
        System.out.println(sb.toString());

        // populate list of primes in range
        List<Integer> generatedPrimes = new ArrayList<>();
        for(int i = startingValue; i <= endingValue; i++) {
            if(isPrime(i)) {
                generatedPrimes.add(i);
            }
        }

        this.duration = System.nanoTime() - startTime;

        return generatedPrimes;
    }

    @Override
    public boolean isPrime(int value) {
        if(this.useBruteForce) {
            return this.bruteForceMethod(value);
        }
        return primeTracker.get(value);
    }

    @Override
    public void useBruteForce(boolean useBruteForce) {
        this.useBruteForce = useBruteForce;
    }

    @Override
    public Long getDuration() {
        return this.duration;
    }

    /**
     * Optimized solution for finding prime values from 2 - stopValue
     * 
     * @param stopValue value to find prime numbers to, ie 2-stopValue
     */
    private void findPrimesUpToValue(int stopValue) {

        if(stopValue > 1) { // check positive
            if(stopValue > primeTracker.maxValueChecked()) { // if false, we have these primes memoized

                primeTracker.checkIfNeedsResize(stopValue); // check and add to dynamic array if needed
                stopValue = primeTracker.size() - 1; // set max value to check to size of primeTracker - 1. Nodes are size 100 right now, so shouldn't at too much extra time. 

                for(int i = 2; i <= primeTracker.maxPrime(); i++) { // mark all new dividends as not prime for each found prime
                    if(primeTracker.get(i).compareTo(true) == 0 && (i * i) < primeTracker.size()) { // prime if true. we only need to use if the square is less than current size, otherwise out of range.
                        // System.out.println("Found Prime: " + i + "\nLast dividend: " + primeTracker.getLastDividend(i));
                        int lastDividend = primeTracker.getLastDividend(i);
                        lastDividend = lastDividend != -1 ? lastDividend : (i * i) - i;
                        for(int j = lastDividend + i; j < primeTracker.size(); j += i) { // mark remaining dividends false and update last dividend
                            primeTracker.set(j, false);
                            primeTracker.setLastDividend(i, j);
                            // System.out.println("Not Prime: " + j);
                        }
                    }
                }
                
                // find next prime after current maxPrime
                int currentPrime = primeTracker.maxPrime() + 1;
                while(!primeTracker.get(currentPrime)) {

                    // Don't think I need this check
                    // if(currentPrime > stopValue) {
                    //     break;
                    // }

                    currentPrime++;
                }

                // mark dividends of currentPrime not prime
                while(currentPrime <= stopValue) {
                    int notAPrime = currentPrime * currentPrime; // start at the square
                    while(notAPrime <= stopValue) { // mark all multiples of currentPrime false
                        primeTracker.set(notAPrime, false);
                        primeTracker.setLastDividend(currentPrime, notAPrime);
                        notAPrime += currentPrime;
                    }

                    // find next prime
                    currentPrime++;
                    while(currentPrime <= stopValue && !primeTracker.get(currentPrime)) {
                        currentPrime++;
                    }
                }

                primeTracker.setMaxPrime();
                primeTracker.setMaxValueChecked(primeTracker.size() - 1); // update max value checked
            }
        }
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
        return value <= 1 ? false : true;
    }

    @Override
    public void printNice(List<Integer> primes) {
        System.out.println("Primes:");
        for(Integer prime : primes) {
            System.out.println("  " + prime);
        }
    }
}
