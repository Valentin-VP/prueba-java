package com.prueba.java.service;

import org.springframework.stereotype.Service;
import com.prueba.java.model.FibonacciResult;
import com.prueba.java.repository.FibonacciResultRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    private final FibonacciResultRepository fibonacciResultRepository;

    @Autowired
    public FibonacciServiceImpl(FibonacciResultRepository fibonacciResultRepository) {
        this.fibonacciResultRepository = fibonacciResultRepository;
    }


    public long getFibonacci(int n) {
        Optional<FibonacciResult> result = fibonacciResultRepository.findById(n);

        if (result.isPresent()) {
            FibonacciResult fibonacciResult = result.get();
            fibonacciResult.setQueryCount(fibonacciResult.getQueryCount() + 1);
            fibonacciResultRepository.save(fibonacciResult);
            return fibonacciResult.getValue();
        }

        long value = calculateFibonacci(n);
        fibonacciResultRepository.save(new FibonacciResult(n, value, 1));
        return value;
    }

    private long calculateFibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n debe ser mayor a 0");
        } else if (n == 1) {
            return 1;
        } else {
            long a = 0, b = 1, aux;
            for (int i = 2; i <= n; i++) {
                aux = a + b;
                a = b;
                b = aux;
            }

            return b;
        }
    }


}
