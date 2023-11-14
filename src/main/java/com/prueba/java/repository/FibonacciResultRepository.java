package com.prueba.java.repository;

import com.prueba.java.model.FibonacciResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FibonacciResultRepository extends JpaRepository<FibonacciResult, Integer> { }
