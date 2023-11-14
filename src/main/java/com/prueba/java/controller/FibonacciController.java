package com.prueba.java.controller;

import com.prueba.java.service.FibonacciService;
import com.prueba.java.service.FibonacciServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("prueba/api/v1/fibonacci")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciServiceImpl service) {
        this.fibonacciService = service;
    }


    @GetMapping("/{n}")
    public ResponseEntity<?> fibonacci(@PathVariable int n) {
        try{
            return ResponseEntity.ok(fibonacciService.getFibonacci(n));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
