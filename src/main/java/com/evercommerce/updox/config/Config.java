package com.evercommerce.updox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evercommerce.updox.prime.PrimeNumberGenerator;
import com.evercommerce.updox.prime.PrimeNumberGeneratorImpl;
import com.evercommerce.updox.prime.PrimeRange;

@Configuration
public class Config {

    @Bean
    public PrimeNumberGenerator primeNumberGenerator() {
        return new PrimeNumberGeneratorImpl();
    }

    @Bean
    public PrimeRange primeRange() {
        return new PrimeRange();
    }
}