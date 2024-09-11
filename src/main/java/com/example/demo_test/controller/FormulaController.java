package com.example.demo_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormulaController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("a") double a,
            @RequestParam("b") double b,
            @RequestParam("c") double c,
            @RequestParam("y") double y,
            @RequestParam(value = "round", required = false) boolean round,
            Model model) {

        double x = calculateX(a, b, c);
        double result = round ? Math.round(x * 10) / 10.0 : x;

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);
        model.addAttribute("y", y);
        model.addAttribute("result", result);
        model.addAttribute("report", generateReport(a, b, c, result));

        return "result";
    }

    private double calculateX(double a, double b, double c) {
        return (c - b) / a;
    }

    private String generateReport(double a, double b, double c, double x) {
        return "a = " + a + "; b = " + b + "; c = " + c + "; x = " + x + ";";
    }
}
