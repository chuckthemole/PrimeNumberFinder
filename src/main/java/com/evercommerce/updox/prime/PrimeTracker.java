package com.evercommerce.updox.prime;

/**
 * Class to optimize/memoize lookup of prime numbers.
 * This can be thought of as an array that dynamically resizes based on how large the number the user is searching for primes
 */
public interface PrimeTracker {

    /**
     * 
     * @param index to check if prime
     * @return if the prime index is prime
     */
    Boolean get(int index);

    /**
     * 
     * @param index to use when setting isPrime
     * @param isPrime is this index prime (true) or not prime (false)
     */
    void set(int index, Boolean isPrime);

    /**
     * 
     * @param index index to get
     * @return last dividend of associated prime index
     */
    int getLastDividend(int index);

    /**
     * 
     * @param index index to set
     * @param dividend last dividend to set of associated prime index
     */
    void setLastDividend(int index, int dividend);

    /**
     * 
     * @return size of this PrimeTracker object
     */
    int size();

    /**
     * 
     * @return max prime found in storage
     */
    int maxPrime();

    /**
     * will find max prime in array and set is as new max prime
     */
    void setMaxPrime();

    /**
     * 
     * @return max prime found in storage
     */
    int maxValueChecked();

    /**
     * 
     * @param max set max prime
     */
    void setMaxValueChecked(int max);

    /**
     * 
     * @param index index to check
     * @return if index is in current bounds of array
     */
    public boolean inCurrentBounds(int index);

    /**
     * 
     * @param stopValue value to find primes to. note this will go to ceiling of NODE_SIZE
     * @return true if dynamically added to PrimeTracker, false if not needed
     */
    boolean checkIfNeedsResize(int stopValue);
}
