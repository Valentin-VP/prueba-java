package com.prueba.java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fibonacci_result")
public class FibonacciResult {

    @Id
    private int index;
    private long value;
    private long queryCount;

    public FibonacciResult() {
    }

    public FibonacciResult(int index, long value, long queryCount) {
        this.index = index;
        this.value = value;
        this.queryCount = queryCount;
    }

    public int getIndex() {
        return index;
    }

    public long getValue() {
        return value;
    }

    public long getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(long queryCount) {
        this.queryCount = queryCount;
    }
}
