package com.evercommerce.updox.prime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.evercommerce.updox.App;
import com.evercommerce.updox.controller.PrimeController;

// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PrimeApplicationTests {

    @Autowired
    private PrimeNumberGenerator generator;

    @Autowired
    private PrimeController controller;

    @Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    // Tests for PrimeNumberGenerator
	@Test
	void firstRange() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(7900, 7920);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 1 Range: 7900 - 7920 * * * ");
        // assertArrayEquals(expectedPrimes.toArray(), generatedPrimes.toArray());
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void firstRangeBruteForce() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        this.generator.useBruteForce(true);
        List<Integer> generatedPrimes = this.generator.generate(7900, 7920);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 2 Range: 7900 - 7920 * * * ");
        // assertArrayEquals(expectedPrimes.toArray(), generatedPrimes.toArray());
        assertEquals(expectedPrimes, generatedPrimes);
        assertEquals(true, this.generator.getDuration().compareTo(Long.valueOf(0)) > 0);
	}

    @Test
	void reverseFirstRange() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(7920, 7900);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 3 Range: 7920 - 7900 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void secondRange() {
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7, 11);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(1, 12);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 4 Range: 1 - 12 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void reverseSecondRange() {
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7, 11);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(12, 1);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 5 Range: 12 - 1 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void thirdRange() {
        List<Integer> expectedPrimes = List.of(101, 103, 107, 109);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(98, 111);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 6 Range: 98 - 111 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void reverseThirdRange() {
        List<Integer> expectedPrimes = List.of(101, 103, 107, 109);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(111, 98);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 7 Range: 111 - 98 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
	void testTwoInARow() {
        List<Integer> expectedPrimes = List.of(7901, 7907, 7919);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(7900, 7920);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 8 Range: 7900 - 7920 * * * ");
        // assertArrayEquals(expectedPrimes.toArray(), generatedPrimes.toArray());
        assertEquals(expectedPrimes, generatedPrimes);

        expectedPrimes = List.of(101, 103, 107, 109);
        generatedPrimes = this.generator.generate(98, 111);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 9 Range: 98 - 111 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
    void upperLowerEqual() {
        List<Integer> expectedPrimes = List.of();
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(12, 12);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 10 Range: 12 - 12 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
    }

    @Test
	void rangeBruteForce() {
        List<Integer> expectedPrimes = List.of(2);
        this.generator.useBruteForce(true);
        List<Integer> generatedPrimes = this.generator.generate(0, 2);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 11 Range: 0 - 2 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
	}

    @Test
    void zeroThroughOne() {
        List<Integer> expectedPrimes = List.of();
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(0, 1);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 12 Range: 0 - 1 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
    }

    @Test
    void upperLowerEqualPrime() {
        List<Integer> expectedPrimes = List.of(2);
        this.generator.useBruteForce(false);
        List<Integer> generatedPrimes = this.generator.generate(2, 2);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 13 Range: 2 - 2 * * * ");
        assertEquals(expectedPrimes, generatedPrimes);
    }

    // Tests for PrimeRange
    @Test
    void testPrimeRange() {
        PrimeRange range = new PrimeRange();
        int upper = 10;
        int lower = 1;
        range.setLower(1);
        range.setUpper(10);
        assertEquals(range.getLower(), lower);
        assertEquals(range.getUpper(), upper);

        // make sure it swaps upper and lower if upper < lower
        range.setLower(10);
        range.setUpper(1);
        assertEquals(range.getLower(), lower);
        assertEquals(range.getUpper(), upper);

        // one more time to cover upper
        range.setLower(10);
        range.setUpper(1);
        assertEquals(range.getUpper(), upper);
        assertEquals(range.getLower(), lower);
    }

    // Tests for PrimeTracker
    @Test
    void testInit() {
        PrimeTracker tracker = new PrimeTrackerImpl();
        assertEquals(null, tracker.get(100));
        assertEquals(100, tracker.size()); // verify size
        assertEquals(true, tracker.inCurrentBounds(9));
        assertEquals(false, tracker.inCurrentBounds(100));
        assertEquals(true, tracker.inCurrentBounds(98));
        assertEquals(true, tracker.inCurrentBounds(99));
        for(int i = 2; i < tracker.size(); i++) { // verify all set to true except 1 and 0
            assertEquals(true, tracker.get(i));
        }
        tracker.set(101, true);
        assertEquals(200, tracker.size()); // verify size
        assertEquals(true, tracker.get(101)); // verify size
    }

    @Test
    void testGettersSetters() {
        PrimeTracker tracker = new PrimeTrackerImpl();
        assertEquals(100, tracker.size());
        tracker.set(405, false);
        tracker.set(102, false);
        tracker.set(345, false);
        assertEquals(500, tracker.size());
        assertEquals(false, tracker.inCurrentBounds(500));
        for(int i = 0; i < tracker.size(); i++) {
            if(i == 0 || i == 1 || i == 405 || i == 102 || i == 345) {
                assertEquals(false, tracker.get(i));
            } else {
                assertEquals(true, tracker.get(i));
            }
        }

        assertEquals(-1, tracker.getLastDividend(500));
        tracker.setLastDividend(500, 5); // index out of bounds
        assertEquals(-1, tracker.getLastDividend(500)); // should still be true

        // tracker.set(0, false);
        // tracker.set(1003, false);
        // tracker.set(13, true);
        // tracker.set(10012, true);
        // tracker.set(1, null);
        // assertEquals(10012, tracker.maxPrime());
    }

    // Controller
    @Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/",String.class).contains("Prime Number Generator"));

        PrimeRange range = new PrimeRange();
        range.setLower(1);
        range.setUpper(10);
        // HttpEntity<String> request = new HttpEntity<String>("{'lower': 1, 'upper': 12}");
        // assertTrue(this.restTemplate.postForObject("http://localhost:" + port + "/generate", request, String.class).contains("Prime Number Generator"));
	}

    // main
    @Test
    public void main() {
        App.main(new String[] {});
    }

    // private methods
    private void debug(List<Integer> expectedPrimes, List<Integer> generatedPrimes, String headline) {
        System.out.println(headline);
        System.out.println("Expected:");
        this.generator.printNice(expectedPrimes);
        System.out.println("Generated:");
        this.generator.printNice(generatedPrimes);
    }
}
