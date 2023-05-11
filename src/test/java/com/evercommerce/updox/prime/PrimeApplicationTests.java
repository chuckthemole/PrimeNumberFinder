package com.evercommerce.updox.prime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

    @Test
    void upperLowerEqual() {
        List<Integer> expectedPrimes = List.of();
        List<Integer> generatedPrimes = this.generator.generate(12, 12);
        debug(expectedPrimes, generatedPrimes, "\n* * * Test 5 Range: 12 - 12 * * * ");
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

    // Controller
    // @Test
	// public void contextLoads() throws Exception {
	// 	assertThat(controller).isNotNull();
	// }
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
