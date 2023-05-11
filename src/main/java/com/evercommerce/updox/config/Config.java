package com.evercommerce.updox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evercommerce.updox.dao.PrimeNumberDao;
import com.evercommerce.updox.dao.PrimeNumberDaoImpl;
import com.evercommerce.updox.prime.PrimeList;
import com.evercommerce.updox.prime.PrimeNumberGenerator;
import com.evercommerce.updox.prime.PrimeNumberGeneratorImpl;
import com.evercommerce.updox.prime.PrimeRange;
import com.evercommerce.updox.ui.View;

@Configuration
public class Config {

    @Bean
    public View view() {
        return new View();
    }

    // @Bean
    // public PrimeNumberDao primeNumberDao() {
    //     PrimeNumberDao dao = new PrimeNumberDaoImpl();
    //     return dao;
    // }

    @Bean
    public PrimeNumberGenerator primeNumberGenerator() {
        return new PrimeNumberGeneratorImpl();
    }

    @Bean
    public PrimeRange primeRange() {
        return new PrimeRange();
    }

    @Bean
    public PrimeList primeList() {
        return new PrimeList();
    }
}

