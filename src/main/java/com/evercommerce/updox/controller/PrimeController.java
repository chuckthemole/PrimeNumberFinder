package com.evercommerce.updox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evercommerce.updox.prime.PrimeNumberGenerator;
import com.evercommerce.updox.prime.PrimeRange;

@Controller
@RequestMapping("/")
public class PrimeController {

    @Autowired
    private PrimeNumberGenerator generator;

    @GetMapping(value = "/")
	public String index(Model model) {
        model.addAttribute("primeRange", new PrimeRange());
		return "index";
	}

    @PostMapping(value = "/generate")
    public String generatePrimeNumbers(@ModelAttribute("primeRange") PrimeRange range, Model model) {
        model.addAttribute("lower", range.getLower());
        model.addAttribute("upper", range.getUpper());
        model.addAttribute("primeList", this.generator.generate(range.getLower(), range.getUpper()));
        return "result";
    }
}

