package com.evercommerce.updox.prime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @RunWith(SpringRunner.class)
@SpringBootTest
class PrimeApplicationTests {

    @Autowired
    PrimeNumberGenerator generator;

	@Test
	void firstRange() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        List<Integer> generatedPrimes = this.generator.generate(7900, 7920);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 1 Range: 7900 - 7920 * * * ");
        // assertArrayEquals(expectedPrimes.toArray(), generatedPrimes.toArray());
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void reverseFirstRange() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        List<Integer> generatedPrimes = this.generator.generate(7920, 7900);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 2 Range: 7920 - 7900 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void secondRange() {
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7, 11);
        List<Integer> generatedPrimes = this.generator.generate(1, 12);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 3 Range: 1 - 12 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void reverseSecondRange() {
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7, 11);
        List<Integer> generatedPrimes = this.generator.generate(12, 1);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 4 Range: 12 - 1 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    private void debug(List<Integer> expectedPrimes, List<Integer> generatedPrimes, String headline) {
        System.out.println(headline);
        System.out.println("Expected:");
        this.generator.printNice(expectedPrimes);
        System.out.println("Generated:");
        this.generator.printNice(generatedPrimes);
    }   
}
