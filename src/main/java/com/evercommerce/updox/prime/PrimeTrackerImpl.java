package com.evercommerce.updox.prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeTrackerImpl implements PrimeTracker {

    private static int NODE_SIZE = 100;
    private List<Node> primes;
    private int maxPrime; // store max prime found
    private int maxValueChecked; // track max index we have traversed up to this point to find primes

    public PrimeTrackerImpl() {
        this.primes = new ArrayList<>();
        this.primes.add(new Node());

        // set 0 and 1 to false, since they are not prime
        this.set(0, false);
        this.set(1, false);

        maxPrime = 1;
        maxValueChecked = 1;
    }

    @Override
    public Boolean get(int index) {
        if(inCurrentBounds(index)) { // check if in bounds
            int nodeIndex = findNode(index);
            Node node = this.primes.get(nodeIndex);
            return node.get(index - (nodeIndex * NODE_SIZE));
        }
        return null;
    }

    @Override
    public void set(int index, Boolean isPrime) {
        int nodeIndex = findNode(index);
        if(!inCurrentBounds(index)) { // need to add nodes
            int nodesToAdd = (nodeIndex + 1) - this.primes.size();
            for(int i = 0; i < nodesToAdd; i++) {
                this.primes.add(new Node());
            }
        }
        this.primes.get(nodeIndex).set(index - (nodeIndex * NODE_SIZE), isPrime); // set node value

        // doing this elsewhere
        // if(isPrime && index > this.maxPrime) { // check if new maxPrime
        //     this.maxPrime = index;
        // }
    }

    @Override
    public int getLastDividend(int index) {
        if(inCurrentBounds(index)) { // check if in bounds
            int nodeIndex = findNode(index);
            Node node = this.primes.get(nodeIndex);
            return node.getLastDividend(index - (nodeIndex * NODE_SIZE));
        }
        return -1;
    }

    @Override
    public void setLastDividend(int index, int dividend) {
        int nodeIndex = findNode(index);
        if(inCurrentBounds(index)) { // check if in bounds
            this.primes.get(nodeIndex).setLastDividend(index - (nodeIndex * NODE_SIZE), dividend);
        }
    }

    @Override
    public boolean checkIfNeedsResize(int stopValue) {
        if(!inCurrentBounds(stopValue)) {
            int nodesToAdd = (findNode(stopValue) + 1) - this.primes.size();
            for(int i = 0; i < nodesToAdd; i++) {
                this.primes.add(new Node());
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.primes.size() * NODE_SIZE;
    }

    @Override
    public int maxPrime() {
        return this.maxPrime;
    }

    @Override
    public void setMaxPrime(int max) {
        this.maxPrime = max;
    }

    @Override
    public int maxValueChecked() {
        return this.maxValueChecked;
    }

    @Override
    public void setMaxValueChecked(int max) {
        this.maxValueChecked = max;
    }

    @Override
    public boolean inCurrentBounds(int index) {
        return index < (this.primes.size() * NODE_SIZE);
    }

    private int findNode(int index) {
        int nodeIndex = 0;
        while(index >= NODE_SIZE) {
            index -= NODE_SIZE;
            nodeIndex++;    
        }
        return nodeIndex;
    }
    
    /**
     * Class to hold nodes of size NODE_SIZE
     * Keeps track of node with boolean values and last dividend a prime used
     */
    private class Node {

        private boolean[] node; // tracks if index is prime
        private int[] lastDividend; // if prime, store that prime's last dividend, else -1

        private Node() {
            this.node = new boolean[NODE_SIZE];
            Arrays.fill(this.node, true);
            this.lastDividend = new int[NODE_SIZE];
            Arrays.fill(this.lastDividend, -1);
        }
        public boolean get(int index) {
            return this.node[index];
        }
        public void set(int index, boolean isPrime) {
            this.node[index] = isPrime;
        }
        public int getLastDividend(int primeIndex) {
            return this.lastDividend[primeIndex];
        }
        public void setLastDividend(int index, int lastDividend) {
            this.lastDividend[index] = lastDividend;
        }
    }
}
