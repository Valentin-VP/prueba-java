package com.prueba.java.service;

import com.prueba.java.model.FibonacciResult;
import com.prueba.java.repository.FibonacciResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceTest {

    private final List<FibonacciResult> fibonacciResultList = new ArrayList<>();

    FibonacciServiceImpl fibonacciService;

    @Mock
    FibonacciResultRepository fibonacciResultRepository;

    @BeforeEach
    public void setUp() {
        fibonacciResultList.add(new FibonacciResult(5, 5, 1));
        fibonacciResultList.add(new FibonacciResult(6, 8, 1));
        fibonacciResultList.add(new FibonacciResult(7, 13, 1));

        fibonacciService = new FibonacciServiceImpl(fibonacciResultRepository);
    }

    @Test
    void testGetFibonacciWhenAlreadyExistsOnDB() {
        when(fibonacciResultRepository.findById(fibonacciResultList.get(0).getIndex()))
                .thenReturn(Optional.ofNullable(fibonacciResultList.get(0)));


        long fibonacciResultFound = fibonacciService.getFibonacci(fibonacciResultList.get(0).getIndex());
        assertEquals(fibonacciResultFound, fibonacciResultList.get(0).getValue());

        verify(fibonacciResultRepository, times(1)).save(any(FibonacciResult.class));
    }

    @Test
    void testGetFibonacciWhenNotExistsOnDB() {
        when(fibonacciResultRepository.findById(fibonacciResultList.get(0).getIndex()))
                .thenReturn(Optional.empty());

        long fibonacciResultFound = fibonacciService.getFibonacci(fibonacciResultList.get(0).getIndex());
        assertEquals(fibonacciResultFound, fibonacciResultList.get(0).getValue());

        verify(fibonacciResultRepository, times(1)).save(any(FibonacciResult.class));
    }
}
