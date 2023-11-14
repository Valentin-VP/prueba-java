package com.prueba.java.controller;

import com.prueba.java.service.FibonacciServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(FibonacciController.class)
class FibonacciControllerTest {

    String basePath = "/prueba/api/v1/fibonacci/";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FibonacciController controller;

    @MockBean
    FibonacciServiceImpl service;

    @Test
    void testFibonacciSuccess() throws Exception{
        when (service.getFibonacci(5)).thenReturn(5L);

        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("5"));

        verify(service).getFibonacci(ArgumentMatchers.eq(5));
    }

    @Test
    void testFibonacciBadRequest() throws Exception{
        when (service.getFibonacci(0)).thenThrow(new IllegalArgumentException("n debe ser mayor a 0"));

        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("n debe ser mayor a 0"));

        verify(service).getFibonacci(ArgumentMatchers.eq(0));
    }
}
